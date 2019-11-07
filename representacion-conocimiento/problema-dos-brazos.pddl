(define (problem p01) 
	(:domain blocksword) 
	(:objects 
		arm1 -arm
		arm2 -arm
		a - cube 
		b - cube
		c - cube
		d - cube
		e -cube
	) 
	(:init (cube_over_table a) (not(cube_on_top a)) (cube_over_table b) (cube_over_cube c a) (cube_over_table e) (cube_on_top b) (cube_on_top c) (cube_on_top e) (not(arm_free arm1)) (arm_holding arm1 d))
	(:goal (and (cube_over_cube a b) (cube_over_cube b c) (cube_on_top a) (cube_over_table c)))
)
