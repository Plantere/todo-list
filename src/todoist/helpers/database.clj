(ns todoist.helpers.database
  (:require [clojure.data.json :as json] [clojure.java.io :refer :all]))

(defn generate-uuid [] (.toString (java.util.UUID/randomUUID)))
;;(defn check-task-exist [name] false)
(defn read-json-file [file]
  (json/read-str (slurp file)))

(defn check-user-exist [data, type] (some (fn [x] (= (get x type) data)) (read-json-file "database/users.json")))
(defn conj-and-to-json [params file]
  (let [users-conj (conj (read-json-file file) params)]
    (-> users-conj (json/write-str :escape-unicode false))))
    
(defn append-json [params file]
  (let [params-converted (conj-and-to-json params file)]
    (with-open [wrtr (writer file)] (.write wrtr params-converted))))
  

(defn delete-by [data type file]
  (let [data-filtered (json/write-str (filter (fn [x] (not= (get x type) data)) (read-json-file file)) :escape-unicode false)]
    (with-open [wrtr (writer file)] (.write wrtr data-filtered))))
    
(defn get-by [data type file]
  (let [data-filtered  (filter (fn [x] (= (get x type) data)) (read-json-file file)) ] data-filtered))
  
(defn get-position
  [pred coll]
  (keep-indexed (fn [idx x] (when (pred x) idx)) coll))

(defn edit-by
  [id type file params]
  (let [json-file (read-json-file file)
        index-id (first (get-position (fn [x] (= (get x type) id)) json-file))]
    (with-open [wrtr (writer file)] (.write wrtr (json/write-str (assoc json-file index-id params) :escape-unicode false)))
    )
  )