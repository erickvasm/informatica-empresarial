#########################################################################
#
#	Conversor de bases numericas
#
#	Autor: Ronald Caravaca
#
#########################################################################


## Funcion que convierte un numero decimal a binario
def decimal_a_binario(decimal, n=None):

	if n == None:
		binario = []
		i = 0
		while decimal > 0:
			residuo = decimal % 2
			binario.append(residuo)
			decimal = decimal // 2
			i += 1

	else:
		binario = [0] * n
		i = 0
		while decimal > 0:
			residuo = decimal % 2
			binario[i] = residuo
			decimal = decimal // 2
			i += 1

	binario.reverse()

	return ''.join([str(bit) for bit in binario])

## Funcion que convierte un numero binario a decimal
def binario_a_decimal(binario):

	decimal = 0
	n = 0
	for bit in binario[::-1]:

		decimal += int(bit) * 2**n
		n += 1

	return decimal

## Funcion que obtiene el complemento a 1 de numero
def complemento_a_1(binario):

	n = len(binario)
	complemento = (2**n - 1) - binario_a_decimal(binario)

	return decimal_a_binario(complemento, n)

## Funcion que obtiene el complemento a 2 de numero
def complemento_a_2(binario):

	n = len(binario)
	complemento = 2**n - binario_a_decimal(binario)

	return decimal_a_binario(complemento, n)	



#############################################
### MAIN
x = int(input("Ingrese in entero: "))

dec_to_bin = decimal_a_binario(x)
bin_to_dec = binario_a_decimal(dec_to_bin)
com_a_1 = complemento_a_1(dec_to_bin)
com_a_2 = complemento_a_2(dec_to_bin)

print("El numero "+ str(x) +" en binario es : ", dec_to_bin)
print("El numero "+ str(dec_to_bin) +" en decimal es : ", bin_to_dec)
print("El complemento a 1 de "+ str(dec_to_bin) +" es: ", com_a_1)
print("El complemento a 2 de "+ str(dec_to_bin) +" es: ", com_a_2)
