(ns todoist.repository.users
  (:require [todoist.helpers.database :refer [generate-uuid append-json check-exist delete-by get-by edit-by read-json-file]]
            [clojure.java.io :refer :all]))


(def table-users "database/users.json")

(defn create
  ([name]
   (let [params {:id (generate-uuid) :name name}]
     (if (not= (check-exist name "name" table-users) true)
       (append-json params table-users)
       (throw (AssertionError. "Name is already being used on the system")))))
  ([] (throw (AssertionError. "name is a mandatory parameter"))))


(defn delete
  ([id]
   (if (= (check-exist id "id" table-users) true)
     (delete-by id "id" table-users)
     (throw (AssertionError. "User not exist in the system"))))
  ([] (throw (AssertionError. "id is a mandatory parameter")))
  )

(defn get-specific
  ([id]
   (if (= (check-exist id "id" table-users) true)
     (get-by id "id" table-users)
     (throw (AssertionError. "User not exist in the system"))))
  ([] (throw (AssertionError. "id is a mandatory parameter")))
)

(defn edit
  ([id name]
   (if (and (= (check-exist id "id" table-users) true) (not= (check-exist name "name" table-users) true))
     (edit-by id "id" table-users {:id id :name name})
     (throw (AssertionError. "User does not exist, or name already being used in the system"))))
   ([] (throw (AssertionError. "id and name is a mandatory parameter"))))

(defn get-all
  []
  (read-json-file table-users)
  )