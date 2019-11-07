;: Practica opcional
;:

(define (domain blocksword)
  (:requirements :typing)
  (:types
	arm - object
	cube - locatable
  )

(:predicates 
	(cube_on_top ?x - cube)
	(cube_over_table ?x - cube)
	(cube_over_cube ?x - cube ?y - cube)
	(arm_free ?x - arm)
	(arm_holding ?arm - arm ?x - cube)
)

(:action coger 
	:parameters (?arm - arm ?cube - cube)
	:precondition (and (cube_on_top ?cube)(cube_over_table ?cube)(arm_free ?arm)) 
	:effect (and (arm_holding ?arm ?cube) (not (cube_on_top ?cube)) (not (cube_over_table ?cube))(not (arm_free ?arm)))
)

(:action soltar 
	:parameters (?arm - arm ?cube - cube) 
	:precondition (and (arm_holding ?arm ?cube)) 
	:effect (and (cube_on_top ?cube) (arm_free ?arm) (cube_over_table ?cube) (not (arm_holding ?arm ?cube)))
)

(:action apilar
	:parameters (?arm - arm ?ob - cube ?underob - cube)
	:precondition (and (cube_on_top ?underob)(arm_holding ?arm ?ob))
	:effect	(and (cube_on_top ?ob) (arm_free ?arm) (cube_over_cube ?ob ?underob) (not (arm_holding ?arm ?ob))(not (cube_on_top ?underob)))
)

(:action desapilar 
	:parameters (?arm - arm ?ob - cube ?underob - cube) 
	:precondition (and (cube_over_cube ?ob ?underob) (cube_on_top ?ob) (arm_free ?arm)) 
	:effect (and (arm_holding ?arm ?ob) (cube_on_top ?underob) (not (cube_over_cube ?ob ?underob)) (not (cube_on_top ?ob)) (not (arm_free ?arm))))
)
