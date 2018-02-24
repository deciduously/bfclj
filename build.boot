(set-env!
 :source-paths #{"src/clj"}
 :dependencies '[[org.clojure/clojure "1.9.0"]])

(task-options!
  aot {:namespace #{'bfclj.core}}
  pom {:project 'bfclj
       :description "Brainfuck intepreter"
       :license {"MIT" "https://github.com/deciduously/bfclj/blob/master/LICENSE"}
       :developers {"deciduously" "dev@deciduously.com"}
       :version "0.0.1"}
  jar  {:main 'bfclj.core
        :file "bfclj.jar"}
  sift {:include #{#"bfclj.jar"}})

(require '[bfclj.core :as b])

(deftask build
  "Builds a production uberjar"
  []
  (comp
   (aot)
   (pom)
   (uber)
   (jar)
   (sift)
   (target)))

(deftask run
  "Run interpreter"
  []
  (b/run))
