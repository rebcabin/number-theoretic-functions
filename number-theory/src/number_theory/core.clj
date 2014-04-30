(ns number-theory.core)

(defmacro pdump
  "Monitoring and debugging macro with semantics of 'identity'."
  [x]
  `(let [x# (try ~x (catch Exception e# (str e#)))]
     (do (println "----------------")
         (clojure.pprint/pprint '~x)
         (println "~~>")
         (clojure.pprint/pprint x#)
         x#)))

(defn- except
  "Throws an Exception with the given string message."
  [s]
   (-> s Exception. throw))

(defn- or-default
  "Fetch first optional value from function arguments preceded by &."
  [val default] (if val (first val) default))

(defn ^:private
  assert
  "Throws an exception if the given Boolean is false."
  [bool & msg-]
  (when-not bool (except (or-default msg- ""))))

(defn ^:private
  assert-not
  "Throws an exception if the given Boolean is true."
  [bool & msg-]
  (when bool (except (or-default msg- ""))))

(defn divides?
  "True if a divides b, in the sense of a mod b == 0."
  [a b] (== 0 (mod b a)))

(defn integer-power
  "Raises a to the power of b, where b >= 0. a need not be positive; b
must not be negative. 0 to the power 0 is reckoned as 1 (see
http://bit.ly/1iFJakv."
  [a b]
  (assert (not (neg? b)))
  (letfn [(helper [a b n]
            (if (zero? b) n (recur a (dec b) (* n a))))]
    (helper a b 1)))

(defn gcd
  "Computes the greatest common divisor (gcd) of a and b and possibly
more integers. None of them may be zero."
  ([a b]
     (assert (and (not= 0 a) (not= 0 b)))
     (if (> a b)
       (recur b a)
       (if (divides? a b)
         a
         (recur (rem b a) a))))
  ([a b & more] (reduce gcd a (cons b more))))
