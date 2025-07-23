LEONCITO WEB - PROTOTIPO 1.2





PASOS PARA EJECUTAR EL PROYECTO



1. USAR JDK24
2. USAR MYSQL8 
3. CREAR UNA BASE DE DATOS LLAMADA leoncito_db
4. Remplazar el nombre y contraseña de tu base de datos desde el archivo application.propierties
5. En el archivo propierties añadir un token de 256 bits para el jwt.secret

EJEMPLO

spring.datasource.username=NOMBRE DE TU BASE DE DATOS
spring.datasource.password=TUCONTRASEÑA

jwt.secret=TU_TOKEM_DE_256_bits_MINIMO
jwt.expiration=3600000



¿Como generar un token de 256 bits?
Hay muchas paginas pero les recomiendo esta:👇
                                  https://jwtsecrets.com/



Realizar un "npm install boostrap"






PLANES A FUTURO:
⭐Tengo pensado refactorizar todo el codigo y aplicar mejores y buenas practicas
⭐Desplegar el proyecto en DOCKER




@creador  Pompinchuz(:3)