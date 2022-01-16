(ns todoist.repository.users
  (:require [todoist.helpers.database :refer [generate-uuid append-json check-user-exist delete-by get-by edit-by read-json-file]]
            [clojure.java.io :refer :all]))
            

(defn create
  ([name]
   (let [params {:id (generate-uuid) :name name}]
     (if (not= (check-user-exist name "name") true)
       (append-json params "database/users.json")
       (throw (AssertionError. "Name is already being used on the system")))))
  ([] (throw (AssertionError. "name is a mandatory parameter"))))


(defn delete
  ([id]
   (if (= (check-user-exist id "id") true)
     (delete-by id "id" "database/users.json")
     (throw (AssertionError. "User not exist in the system"))))
  ([] (throw (AssertionError. "id is a mandatory parameter")))
  )

(defn get-specific
  ([id]
   (if (= (check-user-exist id "id") true)
     (get-by id "id" "database/users.json")
     (throw (AssertionError. "User not exist in the system"))))
  ([] (throw (AssertionError. "id is a mandatory parameter")))
)

(defn edit
  ([id name]
   (if (and (= (check-user-exist id "id") true) (not= (check-user-exist name "name") true))
     (edit-by id "id" "database/users.json" {:id id :name name})
     (throw (AssertionError. "User does not exist, or name already being used in the system"))))
   ([] (throw (AssertionError. "id and name is a mandatory parameter"))))

(defn get-all
  []
  (read-json-file "database/users.json")
  )