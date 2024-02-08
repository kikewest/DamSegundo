import math 

''' Ejercicio 1
temperatura = int(input("Dime la temperatura en Celsius: "))
Fahrenheit = temperatura * 9/5+32
print("la temperatura en Fahrenheit es: " + str(Fahrenheit))
'''
''' Ejercicio 2
num1 = int(input("dime un numero para sumar: "))
num2 = int(input("dime un segundo numero para sumar: "))

def suma(num1, num2):  
    suma=num1+num2
    return suma
print(suma(num1, num2))
'''
'''Ejercicio 3 
def contar_vocales(cadena):
    # Convertir la cadena a minúsculas para hacer la comparación de manera insensible a mayúsculas
    cadena = cadena.lower()
    vocales = "aeiou"
    contador_vocales = 0
    # Iterar sobre cada carácter en la cadena (for each)
    for caracter in cadena:
        # Verificar si el carácter es una vocal(como un .contains en java)
        if caracter in vocales:
            contador_vocales += 1
    return contador_vocales
entrada_usuario = input("Ingrese una cadena de texto: ")
resultado = contar_vocales(entrada_usuario)
print(f"El número de vocales en la cadena es: {resultado}")
'''
'''Ejercicio 4 ( in range (1,20) para que no empienze en 0)
for i in range(1,20):
    if i%2==0:
        print(i)
'''
'''Ejercicio 5
def calcularFactorial(numero):
    resultado = 1
    for i in range(1, numero + 1):
        resultado *= i
    return resultado
numeroIngresado = int(input("Ingrese un número para calcular su factorial: "))
resultadoFactorial = calcularFactorial(numeroIngresado)
print(f"El factorial de {numeroIngresado} es: {resultadoFactorial}")
'''
'''Ejercicio 6
cuadrados = [x*x for x in range(10)]
print(cuadrados)
'''
'''Ejercicio 7
def valor(id, libro):
    # Verificar si la id esta en libro
    if id in libro:
        return libro[id] #Nos retorna el libro segun la id dada
    else:
        return f"La clave '{id}' no fue encontrada en el diccionario."

biblioteca = {1:"Harry Potter", 2: "El Señor de los Anillos", 3: "Ta weno"}

resultado = valor(3, biblioteca)
print(resultado)
'''
'''Ejercicio 8
texto= "esto es, una cadena de texto, de prueba"
def contar_palabras(cadena):
    # Utilizar el método split para dividir la cadena en palabras
    palabras = cadena.split(",")
    print(palabras)
    # Devolver el número de palabras
    return len(palabras)
 
cantidad_palabras = contar_palabras(texto)

# Mostrar el resultado
print(f"El número de palabras en la cadena es: {cantidad_palabras}")
'''
'''Ejercicio 9
def dividir(num1,num2):
    try:
        res=num1/num2
        return res
    except  ZeroDivisionError:
        print("Error no se puede dividir entre 0 mamawebo")
print (dividir(2,2))   
print (dividir(2,0))
'''
'''Ejercicio 10
def suma(*numeros):
    return sum(numeros)
print(suma(1, 2, 3, 4))
'''
'''Ejercicio 11
class Persona:
    def __init__(self,nombre,edad):
        self.nombre = nombre
        self.edad = edad
    def saludo(self):
            return f'hola, mi nombre es {self.nombre}'
kike = Persona('kike',30)
print (kike.saludo())


class Estudiante(Persona):
    def __init__(self,nombre,edad,carrera):
        super().__init__(nombre,edad)
        self.carrera = carrera
kike = Estudiante('kike',30,'100 metros valla')
print (kike.saludo() +' y estoy en la carrera de ' +kike.carrera)
'''
'''Ejercicio 12
with open('archivo.txt', 'w')as f:
    f.write('hola mi hente estoy doramio')
with open('archivo.txt','r') as f:
    contenido = f.read()
    print(contenido)
'''
class Libro:
    def __init__(self,titulo,autor):
        self.titulo = titulo
        self.autor = autor
    def toString(self):
        return f'Titulo {self.titulo} y Autor {self.autor}'
L = Libro('ta bueno', 'Paco el pescadero')
print(L.toString())

class Persona:
    def __init__(self,nombre,edad):
        self.nombre = nombre
        self.edad = edad
    def saludo(self):
            return f'hola, mi nombre es {self.nombre}'
kike = Persona('kike',30)
print (kike.saludo())

class Estudiante(Persona):
    def __init__(self,nombre,edad,carrera,asignatura):
        super().__init__(nombre,edad)
        self.carrera = carrera
        self.asignatura = asignatura
kike = Estudiante('kike',30,'100 metros valla','Calentamiento')
print (kike.saludo() +' y estoy en la carrera de ' +kike.carrera+' y la Asignatura de ' +kike.asignatura)


class AreaCirculo:

    def Calculo(radio):
        area= math.pi*radio*radio
        print ('El area de su circunferecia es: ' , area)
AreaCirculo.Calculo(10)
'''
class miError(Exception):
    def __init__(self,Mensaje = 'algo salio mal'):
    self.mensaje=mensaje
    '''

try:
    with open('archivo.txt', 'w') as f:
        f.write('hola mi hente estoy doramio')
except miError as e:
        print(e)

try:
    nombre = 'archivo1.txt'
    assert nombre== 'archivo.txt' , 'El nombre esta mal'
    with open(nombre, 'r') as f:
        
        contenido = f.read()
        num = len(contenido.split())
        print('El archivo tiene:', num, 'palabras')
except FileNotFoundError as e:
    print (e)

