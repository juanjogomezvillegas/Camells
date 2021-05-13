# Camells
L'objectiu del joc és representar el joc de carreres de camells. El joc de carreres de camells consisteix en que cada jugador tira una pilota i avança tantes posicions com el número en el que ha aconseguit entrar la pilota. Si la pilota no entra no avança..

Activitat
Carrera de camells
1. Desenvolupeu el programa per representar gràficament la carrera de camells.
  1. Podeu representar els jugadors tant amb imatges com amb figures però poden ser com a màxim de 50 píxels d'ample.
  2. Cada jugador tindrà una possibilitat d'avançar o no, que s'avaluarà primer, i si ha d'avançar ho farà un número aleatori de píxels de 1 a 15.
  3. La sortida serà el costat esquerra de la pantalla i guanya el jugador que toqui el costat dret de la finestra.
    - La finestra serà de 900px d'ample.
    - Cal acabar la ronda abans de poder donar per acabada la partida.
    - Si hi ha un empat guanya al que li hagin sobrat més punts

L’herència dels camells
2. Modifiqueu el programa anterior perquè permeti als camells “professionals” participar en les carreres.
  1. Hi haurà camells Ràpids que en cas de que el número que treguin sigui el valor màxim avançaran el doble de píxels. Per exemple si surt un 20 avancen 40 pixels.
  2. En canvi els camells Fondistes sempre obtindran un valor entre [5,10].
  3. Els camells AntiSenars només avançaran normalment si el valor que treuen és un número parell ( si el valor és senar avançaran 2 píxels).
  4. Els camells Flipats avançaran un cop endavant i un cop enrere.
