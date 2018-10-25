

(deftemplate puerta
	(slot origen)
	(slot destino)
)

(deftemplate objeto
	(slot nombre)
	(slot sala)
	(slot altura (allowed-symbols nil suelo techo))
	(slot lugar)
)

(deftemplate mono
	(slot sala)
	(slot encima-de)
	(slot sostiene)
	(slot hambre (allowed-symbols si no))
)

(deffacts base-hechos
	(puerta (origen comienzo) (destino sala1))
	(puerta (origen sala1) (destino sala2))
	(puerta (origen sala2) (destino sala3))
	(puerta (origen sala3) (destino sala4))

	(mono (sala comienzo) (encima-de suelo) (sostiene nada) (hambre si))
	(objeto (nombre mesa) (sala sala4) (lugar fondo))
	(objeto (nombre banana) (sala sala4) (lugar fondo) (altura techo))
)

;(defrule puerta-simetrica
;	(declare (salience 50))
;	(puerta (origen ?o) (destino ?d))
;	=>
;	(assert (puerta (destino ?o) (origen ?d)))
;)


(defrule mover-mono
	?lm <- (mono (sala ?origen) (encima-de suelo) (sostiene nada) (hambre si))
	(puerta (origen ?origen) (destino ?destino))
	=> 
	(assert (mono (sala ?destino) (encima-de suelo) (sostiene nada) (hambre si)))
   	(retract ?lm)
   	(printout t "mover de "?origen" a "?destino crlf)
)

(defrule subir-mesa
	?lm <- (mono (sala ?sala) (encima-de suelo) (sostiene nada) (hambre si))
	(objeto (nombre mesa) (sala ?sala) (lugar fondo))
	(objeto (nombre banana) (sala ?sala) (lugar fondo) (altura ?alturaBanana))
=> 
   (assert (mono (sala ?sala) (encima-de mesa) (sostiene nada) (hambre si)))
   (retract ?lm)
   (printout t "sube a la mesa" crlf)
)

(defrule moverMesa 
	(mono (sala ?sala) (encima-de suelo) (sostiene nada))
	?lc <- (objeto (nombre mesa) (sala ?sala) (lugar ?lugarMesa))
	(objeto (nombre banana) (sala ?sala) (lugar fondo) (altura techo))
=> 
   (assert (objeto (nombre mesa) (sala ?sala) (lugar fondo)))
   (retract ?lc)
   (printout t "mueve mesa al fondo" crlf)
)

(defrule coger-banana-techo
	?lm <- (mono (sala ?sala) (encima-de mesa) (sostiene nada) (hambre si))
	(objeto (nombre banana) (sala ?sala) (altura techo))
=> 
   (assert (mono (sala ?sala) (encima-de mesa) (sostiene banana) (hambre si)))
   (retract ?lm)
   (printout t "coge la banana" crlf)
)

(defrule coger-banana-suelo
	?lm <- (mono (sala ?sala) (encima-de suelo) (sostiene nada) (hambre si))
	(objeto (nombre banana) (sala ?sala) (altura suelo))
	=>
	(assert (mono (sala ?sala) (encima-de suelo) (sostiene banana) (hambre si)))
   	(retract ?lm)
   	(printout t "coge la banana" crlf)
)

(defrule comer-banana-mesa
	?lm <- (mono (sala ?sala) (encima-de ?superficie) (sostiene banana) (hambre si))
=> 
	(assert (mono (sala ?sala) (encima-de ?superficie) (sostiene nada) (hambre no)))
   (retract ?lm)
   (printout t "come la banana" crlf)
)
