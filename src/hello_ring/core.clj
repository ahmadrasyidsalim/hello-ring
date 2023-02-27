(ns hello-ring.core
  (:require [ring.middleware.params :refer [wrap-params]]
            [ring.util.response :refer [response content-type]]
            [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

(defn page
  [name]
  (str "<html></body>"
       (if name
         (str "Nice to meet you, " name "!")
         (str "<form>"
              "Name: <input name='name' type='text' />"
              "<input type='submit' />"
              "</form>"))
       "</body></html>"))

(defn handler
  [{{name "name"} :params}]
  (-> (response (page name))
      (content-type "text/html")))

(defn -main
  [& args]
  (run-jetty (wrap-params handler)
             {:port 3000
              :join? false}))
