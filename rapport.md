# Rapport Laboratoire 3 - DAA

##### Auteurs: Eva Ray, Massimo Stefani, Rachel Tranchida
##### Date : 30 octobre 2024

## Choix d'implémentation

## Questions complémentaires

> __4.1__ Pour le champ remark, destiné à accueillir un texte pouvant être plus long qu’une seule ligne,
> quelle configuration particulière faut-il faire dans le fichier XML pour que son comportement
> soit correct ? Nous pensons notamment à la possibilité de faire des retours à la ligne, d’activer
> le correcteur orthographique et de permettre au champ de prendre la taille nécessaire.

> __4.2__ Pour afficher la date sélectionnée via le DatePicker nous pouvons utiliser un DateFormat
> permettant par exemple d’afficher 12 juin 1996 à partir d’une instance de Date. Le formatage
> des dates peut être relativement différent en fonction des langues, la traduction des mois par
> exemple, mais également des habitudes régionales différentes : la même date en anglais
> britannique serait 12th June 1996 et en anglais américain June 12, 1996. Comment peut-on
> gérer cela au mieux ?

> __4.3__ Veuillez choisir une question en fonction de votre choix d’implémentation :
> a. Si vous avez utilisé le DatePickerDialog1 du SDK. En cas de rotation de l’écran du
> smartphone lorsque le dialogue est ouvert, une exception android.view.WindowLeaked
> sera présente dans les logs, à quoi est-elle due ?
> b. Si vous avez utilisé le MaterialDatePicker2 de la librairie Material. Est-il possible de limiter
> les dates sélectionnables dans le dialogue, en particulier pour une date de naissance il est
> peu probable d’avoir une personne née il y a plus de 110 ans ou à une date dans le futur.
> Comment pouvons-nous mettre cela en place ?

> __4.4__ Lors du remplissage des champs textuels, vous pouvez constater que le bouton « suivant »
> présent sur le clavier virtuel permet de sauter automatiquement au prochain champ à saisir. 
> Est-ce possible de spécifier son propre ordre de remplissage du questionnaire ?
> Arrivé sur le dernier champ, est-il possible de faire en sorte que ce bouton soit lié au bouton
> de validation du questionnaire ?

> __4.5__ Pour les deux Spinners (nationalité et secteur d’activité), comment peut-on faire en sorte que
> le premier choix corresponde au choix null, affichant par exemple le label « Sélectionner » ?
> Comment peut-on gérer cette valeur pour ne pas qu’elle soit confondue avec une réponse ?

