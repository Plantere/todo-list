(ns todoist.repository.users-test
  (:require 
   [clojure.test :refer [deftest is testing]]
   [todoist.repository.users :as users]))
   

(deftest users-insert-test
  (testing "insert user correct")
  (testing "insert user without params")
  (testing "insert user repeating name"))

(deftest users-edit-test
  (testing "editing user correct")
  (testing "editing user without params")
  (testing "editing user repeating name")
  (testing "editing user non-existent"))
  

(deftest users-delete-test
  (testing "delete user correct")
  (testing "delete user non-exitent"))
  