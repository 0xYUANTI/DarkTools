;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; SUITUP
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;_* Declarations =====================================================
(ns darktools.suitup
  (:refer-clojure :exclude [== >= <= > < =])
  (:require [darktools.armordb :as armordb])
  (:use clojure.core.logic)
  (:use clojure.core.logic.arithmetic))

(declare suits sym->num rank sum)

;;;_* Code =============================================================
(defn best
  "Find a suit which covers BODY-PARTS and provides at least MIN-POISE
   while costing no more than MAX-WEIGHT."
  [min-poise max-weight body-parts]
  (rank (suits min-poise max-weight body-parts)))

(defn rank [candidates] (sort-by #(sum :def %) clojure.core/> candidates))

(defn sum [key maps] (apply clojure.core/+ (cons 0.0 (map key maps))))

(defn suits
  "Returns all possible suits for BODY-PARTS which cost less than MAX-WEIGHT."
  ;; Functional interface
  ([min-poise max-weight body-parts]
     (let [positions (map sym->num body-parts)]
       (run* [q] (suits min-poise max-weight positions q))))
  ;; Relational implementation
  ([min-poise max-weight positions res]
     (conde
      ;; Base case
      ((== positions ())
       (<= min-poise 0)
       (== res ()))
      ;; Step
      ((fresh [name position poise weight
               physical magic fire lightning bleed poison curse
               min-poise1 max-weight1 positions1 res1 piece]
        ;; Candidate for current body part
        (conso position positions1 positions)
        (armordb/armor name position poise weight
                       physical magic fire lightning bleed poison curse)
        ;; Keep track of poise
        (project [min-poise poise]
          (== min-poise1 (- min-poise poise)))
        ;; Enforce weight limit
        (project [max-weight weight]
          (== max-weight1 (- max-weight weight))
          (>= max-weight1 0))
        ;; Nicer output
        (== piece {:name   name
                   :weight weight
                   :poise  poise
                   :def    physical}) ;[physical magic fire lightning bleed poison curse]
        ;; Recur
        (conso piece res1 res)
        (suits min-poise1 max-weight1 positions1 res1))))))

(defn sym->num [sym] (first (run* [q] (armordb/position sym q))))

;;;_* Emacs ============================================================
;;; Local Variables:
;;; allout-layout: t
;;; End:
