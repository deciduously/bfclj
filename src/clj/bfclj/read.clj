(ns bfclj.read)

;; Validates a string input before passing it to the parser.

(defn ends-with-newline?
  "Make sure it ends with a newline"
  [s]
  (= \newline (last s)))

;; TODO only allow a newline as the terminating character
(defn valid-tokens?
  "Check that all tokens are valid"
  [s]
  (let [valids #{\+ \- \[ \] \, \. \< \> \newline}]
    (reduce #(and % (contains? valids %2)) true s)))

(def is-bracket? (partial contains? #{\[ \]}))

(def just-brackets #(->> %
                         (partition-by is-bracket?)
                         (filter (comp is-bracket? first))
                         flatten))

(defn loops-valid?
  "Check for matching loop brackets"
  [s]
  (let [brackets (just-brackets s)]
    (if (or (empty? brackets) (even? (count brackets))) ; If it's not even or zero, just stop
      (cond
        (= \] (first brackets)) false ; We never start with an open
        (not= (count (filter (partial = \[) brackets)) ; And otherwise closes will always equal opens
              (count (filter (partial = \]) brackets))) false
        :otherwise true)
      false)))

(defn valid-program?
  [s]
  (let [validators [(ends-with-newline? s)
                    (valid-tokens? s)
                    (loops-valid? s)]]
    (reduce #(and % %2) true validators)))
