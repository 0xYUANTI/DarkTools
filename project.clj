(defproject darktools "0.1.0-SNAPSHOT"
  :description  "Dark Souls character planner."
  :url          "http://github.com/cannedprimates/DarkTools"
  :license      {:name "BSD"
                 :url  "http://opensource.org/licenses/BSD-3-Clause"}
  :dependencies [[org.clojure/clojure    "1.4.0"]
                 [org.clojure/core.logic "0.7.5"]]
  :main         darktools.core
  :jvm-opts     ["-Xms4G" "-Xmx4G"])
