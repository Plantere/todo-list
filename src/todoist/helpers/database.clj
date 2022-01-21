(ns todoist.helpers.database
  (:require [clojure.data.json :as json] [clojure.java.io :refer :all]))

(defn generate-uuid [] (.toString (java.util.UUID/randomUUID)))
;;(defn check-task-exist [name] false)
(defn read-json-file [table]
  (json/read-str (slurp table)))

(defn check-exist [data type table] (some (fn [x] (= (get x type) data)) (read-json-file table)))
(defn conj-and-to-json [params table]
  (let [users-conj (conj (read-json-file table) params)]
    (-> users-conj (json/write-str :escape-unicode false))))
    
(defn append-json [params table]
  (let [params-converted (conj-and-to-json params table)]
    (with-open [wrtr (writer table)] (.write wrtr params-converted))))
  

(defn delete-by [data type table]
  (let [data-filtered (json/write-str (filter (fn [x] (not= (get x type) data)) (read-json-file table)) :escape-unicode false)]
    (with-open [wrtr (writer table)] (.write wrtr data-filtered))))
    
(defn get-by [data type table]
  (let [data-filtered  (filter (fn [x] (= (get x type) data)) (read-json-file table)) ] data-filtered))
  
(defn get-position
  [pred coll]
  (keep-indexed (fn [idx x] (when (pred x) idx)) coll))

(defn edit-by
  [id type table params]
  (let [json-file (read-json-file table)
        index-id (first (get-position (fn [x] (= (get x type) id)) json-file))]
    (with-open [wrtr (writer table)] (.write wrtr (json/write-str (assoc json-file index-id params) :escape-unicode false)))
    )
  )