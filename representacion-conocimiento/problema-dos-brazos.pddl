(define (problem p01) 
	(:domain blocksword) 
	(:objects 
		arm1 -arm
		arm2 -arm
		a - cube 
		b - cube
		c - cube
		d - cube
		e - cube
	) 
	(:init (cube_over_table a) (not(cube_on_top a)) (cube_over_table b) (cube_on_top b) (cube_over_cube c a) (cube_on_top c) (arm_holding arm1 d) (not(cube_on_top d)) (not(cube_over_table d)) (cube_over_table e) (cube_on_top e) (not(arm_free arm1)) (arm_free arm2))
	(:goal (and (cube_on_top a) (cube_over_cube a b) (cube_over_cube b c) (cube_over_table c) (cube_on_top d) (cube_over_cube d e) (cube_over_table e) (arm_free arm1)(arm_free arm2)))
)
