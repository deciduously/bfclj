(set-env!
 :source-paths #{"src/clj" "test"}
 :resource-paths #{"resources/public"}
 :dependencies '[[org.clojure/clojure "1.9.0"]
                 [adzerk/boot-test "1.2.0" :scope "test"]
                 [com.rpl/specter "1.1.0"]
                 [tolitius/boot-check "0.1.9" :scope "test"]])

(task-options!
  aot {:namespace '#{bfclj.core}}
  pom {:project 'bfclj
       :description "Brainfuck intepreter"
       :license {"MIT" "https://github.com/deciduously/bfclj/blob/master/LICENSE"}
       :developers {"deciduously" "dev@deciduously.com"}
       :version "0.0.1"}
  jar  {:main 'bfclj.core
        :file "bfclj.jar"}
  sift {:include #{#"bfclj.jar"}})

(require '[bfclj.core :as b]
         '[adzerk.boot-test :refer :all]
         '[tolitius.boot-check :as check])

(deftask check-sources
  "Run static analyzers/linters"
  []
  (comp
   (check/with-yagni)
   (check/with-eastwood)
   (check/with-kibit)
   (check/with-bikeshed)))

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

;(deftask run
;  "Run interpreter"
;  []
;  (b/run hello-world))
