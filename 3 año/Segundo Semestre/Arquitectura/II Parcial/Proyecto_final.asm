;###################################
;Esteban Rosales Mora B96967
;Jourgen Anton Murillo B90458
;Aldahir Chaves Mora B92175
;Cesar Morales Villegas B95329
;Erick Vazques Murillo B98334
;###################################

            ORG         $1100 ;Inicio para guardar la constante
            DB          #100  ;Guardamos nuestra constante 100 que es el limite 100km/m
            
            ORG         $1120 ;Inicio del programa
            LDAA        #0    ;Se inicializa el acumulador A en 0
LOOP        CMPA        $1100 ;Se compara lo que hay en el acumulador A con lo que haya en la posicion 1100
            BEQ         SIGA  ;Si son iguales debe dar un salto a la etiqueta SIGA
            INCA              ;Sino el acumulador se incrementa en 1
            BRA         LOOP  ;Se devuelve a la etiqueta LOOP despues de haber incrementado
SIGA        STAA        $1000 ;Se guarda lo que hay en el acumulador A en la posicion 1000

            TFR         A,B   ;Pasamos lo que hay en A hacia B
            SEX         B,D   ;Pasamos lo del acumulador B hacia D y lo convertimos de 8 a 16 bits dado que el acumulador D trabaja con 16 bits

            LDY         #5    ;Cargamos en el registro Y un 5
            EMULS             ;Multiplicamos D por el registro Y
            LDX         #18   ;Cargamos en el registro X un 18
            EDIVS             ;Dividimos D/18
            TFR         Y,D   ;Transferimos del registro Y al acumulador D
            
            STD        $1005  ;Guardar el contenido del acumulador D en la posicion 1005 de memoria
            LDAA        #16   ;Guardamos el valor de 16 en el acumuladorf A
            TAP               ;Lo cargamos en el CCR para encender la bandera I
            

            STOP              ;Fin del programa