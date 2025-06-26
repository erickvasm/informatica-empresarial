#########################################################################
#
#	Este es un pequeno tutorial para pricipientas en python version 3.9
#	Basado en: https://docs.python.org/es/3/tutorial/
#
#	Autor: Ronald Caravaca
#
#########################################################################


'''
	esto es un bloque
	de comentarios
'''


### Numnero en python

A = 8
B = 3

C = A + B

print("La suma de A y B es igual a: ", C)

C = A - B

print("La resta de A y B es igual a: ", C)

C = A * B

print("La multiplicacion de A y B es igual a: ", C)

C = A / B

print("La division de A y B es igual a: ", C)

C = A // B

print("La division entera de A y B es igual a: ", C)

C = A % B

print("El residuao de dividir A entre B es igual a: ", C)

print("El resultado de elevar 2 a la 3 es: ", 2**3)


### Cadenas de caracteres

cadena = 'Hola'
cadena2 = 'Mundo'

print(cadena + " " + cadena2)
print(cadena + "\"" + cadena2)

hola_mundo = cadena + " " + cadena2

print(cadena[0])

print(hola_mundo[0:3])

### Listas

lista_numeros = [1, 4, 9, 16, 25]

lista_char = ['a', 'b', 'c']

print(lista_char)

lista_char.append('d')

print(lista_char)

lista_char.remove('d')

print(lista_char)

lista_char.pop(0)

print(lista_char)

### Tuplas

t = 123, 45, 'hola'

print(t)

### Diccionarios

Persona = {'Nombre': "Juan", 'Apellido': "Perez", 'Edad': 20}

print(Persona['Nombre'])
print(Persona.values())

### Algunos bucles (loops)

# Fibonacci:
a, b = 0, 1
print('Usando el while')
while a < 20:
	print(a)
	a, b = b, a+b

a, b = 0, 1
print('Usando el for')
for i in range(10):
	print(a)
	a, b = b, a+b

print('Usando el while y el if')
a, b = 0, 1
while True:
	print(a)
	a, b = b, a+b
	if a >= 20:
		break

x = int(input("Ingrese un entero: "))

if x < 0:
	print('Negativo')
elif x == 0:
	print('Cero')
elif x == 1:
	print('Uno')
else:
	print('Mayor a cero')

### Funciones

def fibonacci(cantidad):

	a, b = 0, 1
	fib = []
	for i in range(cantidad):
		fib.append(a)
		a, b = b, a+b

	return fib


var = fibonacci(30)
print(var)

print(len(var))


import numpy as np

Array = np.array([[1, 2, 3],[4, 5, 6],[7, 8, 9]])

print(Array)

