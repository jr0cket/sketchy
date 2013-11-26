(defproject sketchy ""

  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  
  :dependencies [[org.clojure/clojure "1.5.1"]

                 [ring/ring-core "1.2.0"]
                 [ring/ring-devel "1.1.8"]
                 [compojure "1.1.5"]
                 [hiccup "1.0.4"]

                 [prismatic/dommy "0.1.1"]

                 [org.clojure/core.async "0.1.242.0-44b1e3-alpha"]
                 [org.clojure/clojurescript "0.0-2014"]
                 [org.clojure/tools.reader "0.7.10"]

                 [jarohen/chord "0.2.0"]]

  :plugins [[jarohen/lein-frodo "0.2.3"]
            [lein-cljsbuild "1.0.0-alpha2"]
            [lein-pdo "0.1.1"]]

  :frodo/config-resource "sketchy-config.edn"

  :source-paths ["src/clj"]

  :resource-paths ["resources" "target/resources"]

  :cljsbuild {:builds [{:source-paths ["src/cljs"]
                        :compiler {:output-to "target/resources/js/sketchy.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]}

  :aliases {"start-server" ["pdo" "cljsbuild" "auto," "frodo"]})
