(ns todoist.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [compojure.core :refer [defroutes]]
            [compojure.route :refer [not-found]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [todoist.routes :as routes]
            )
  (:gen-class))

(defroutes routes
  routes/users
  routes/tasks
  (not-found
   {:error "Not found"}))

(def app
  (-> routes
      (wrap-json-body {:keywords? true :bigdecimals? true})
      wrap-json-response))

(defn -main
  []
  (run-jetty app {:port 3000}))