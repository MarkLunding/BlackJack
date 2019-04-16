#https://nl.wikipedia.org/wiki/Blackjack

 

De Black Jack wordt in de commandline gemaakt.

Het zal een werkend spel moeten zijn bij oplevering.

Doordat het een commandline spel is mag je natuurlijk creatief zijn in de interactie met de gebruiker. H4 kan een harten 4 zijn. Zolang de onderliggende techniek maar werkt.

Op de wikipedia pagina zie je dat er heel veel regels, uitbreidingen en varianten zijn voor een compleet BlackJack spel.

De kunst is als programmeur om deze op te knippen en met kleine doelstellingen te beginnen.

Dat werk heb ik al een beetje voor je gedaan. Hou daarom als leidraad de volgende doelstellingen aan:

 

Doelstelling 1
=

Elke Black Jack begint met het tonen van de geschudde kaarten.

De Black Jack spelen we met 1 spel van 52 kaarten. Alle kaarten zitten dus 1 keer in het overzicht, en als het spel opnieuw gestart wordt is de volgorde in een random nieuwe volgorde.

 

Doelstelling 2
=

Een speler kan meedoen met het spel. De speler kan door een 'k' in te voeren om een kaart vragen. Een speler kan altijd met heel het spel stoppen door 'q' in te voeren. De speler mag ook 'p' invoeren om te passen.

Als een speler een kaart vraagt krijgt hij de bovenste kaart van de stapel. Het totaal aantal punten wordt getoont.

De speler mag met 0 kaarten starten. De aas mag als een fixed 11 punten kaart beschouwd worden. 

Als de speler past, worden alle kaarten getoont die hij heeft met het totaal aantal punten van de kaarten.

De punten per kaart zijn: De waarde van het getal bij de 2, 3, 4, 5, 6, 7, 8, 9, 10. De Boer, Vrouw, Heer zijn 10 punten. De aas is zoals gezegd 11 punten.

 

Doelstelling 3
=

Vanaf hier wordt het spannender. Het is vanaf deze doelstelling verplicht om met kaarten als objecten te werken. Kaart kaart = new Kaart(). De vorige doelstellingen konden prima op andere wijzen volbracht worden, vanaf hier is Object Georienteerd programmeren een vereiste.

De speler krijgt twee kaarten toebedeeld wanneer hij start, zoals bij de werkelijke regels. Ook kan de Aas 1 of 11 punten waard zijn. Dat is afhankelijk van het gegeven of hij anders boven de 21 punten uit komt.

Als een speler niet op tijd past, is de speler af.

Er mag geen invoer zijn die het programma doet vastlopen.

 
Doel 4?
=
Mocht je tijd over hebben dan kun je je bezig gaan houden met meerdere spelers, een bank of zelfs gokken en inzetten.
