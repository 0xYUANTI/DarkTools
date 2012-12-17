;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; Miscellaneous functions.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;_* Declarations =====================================================
(ns darktools.core
  (:require [darktools.suitup :as suitup]))

(declare suitup pp usage)

;;;_* Code =============================================================
(defn -main [tool & args]
  (case tool
    "suitup" (suitup args)
    (usage)))

(defn suitup [[min-poise max-weight & body-parts]]
  (pp (suitup/best (read-string min-poise)
                   (read-string max-weight)
                   (map keyword body-parts))))

(defn pp [suits]
  (doseq [suit suits]
    (doseq [piece suit]
      (println (:name piece)))
    (printf "Weight: %.1f Poise: %.1f Def: %.1f\n\n"
            (suitup/sum :weight suit)
            (suitup/sum :poise  suit)
            (suitup/sum :def    suit))))

(defn usage []
  (println "Usage: lein run suitup min-poise max-weight body-part ..."))

;;;_* Emacs ============================================================
;;; Local Variables:
;;; allout-layout: t
;;; End:
