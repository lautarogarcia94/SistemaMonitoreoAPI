#SistemaMonitoreoAPI
Esta Api, desarrollada con SpringBoot, recibe mediante un mensaje HTTP informacion de mediciones de 4 sensores diferentes. El sistema de 
monitoreo recibe estos valores y cada 30 segundos calcula tres parámetros: promedio, valor máximo y valor mínimo buscando alguna de las 
siguientes anomalías: 
● La diferencia entre el valor mínimo y máximo recibido sea mayor a una constante S (configurable) 
● El valor promedio sea superior a una constante M (configurable) 

En caso de detectar alguna de las situaciones mencionadas en los puntos anteriores, se muestra por pantalla un mensaje de error 
indicando esta situación. Ademas luego de realizar los calculos, existan anomalias o no, se persisten los resultados del calculo en Firebase,
en Cloud Firestore. 
Cada sensor envia 2 mediciones por segundos, en forma independiente y potencialmente simultánea. Estas mediciones son almacenadas en una cola, 
respetando el orden de ingreso al sistema.

Para registrar una medicion, es necesario hacer un POST Request al endpoint "http://localhost:8080/monitoring" y enviar en el cuerpo de la 
peticion el valor de la medicion: {"medicion": "113"}

Para obtener una lista de todos los calculos realizados, es necesario hacer un GET Request al endpoint "http://localhost:8080/monitoring"


#SistemaMonitoreoDemo
Este programa simula los mensajes enviados por la API y realiza 4 POST Request, con datos generados aleatoriamente, cada 500ms.
