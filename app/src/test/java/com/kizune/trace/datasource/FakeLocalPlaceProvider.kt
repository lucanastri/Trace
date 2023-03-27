package com.kizune.trace.datasource

import android.location.Location
import com.kizune.trace.R
import com.kizune.trace.model.Place
import com.kizune.trace.model.PlaceCategory

object FakeLocalPlaceProvider {
    val placesList = listOf(
        Place(
            image = R.drawable.cafe1,
            name = "Caffetteria della Piazza",
            category = PlaceCategory.CAFE,
            address = "Via della Piazza, 28",
            phone = "359730679",
            description = "Venite a bere il caffè nella caffetteria della Piazza, una storica " +
                    "caffetteria nata nel 1924",
            latitude = 38.136949,
            longitude = 13.341157,
            rating = 4.3
        ),

        Place(
            image = R.drawable.cafe2,
            name = "Cat Cafè",
            category = PlaceCategory.CAFE,
            address = "Via dei gattini, 13",
            phone = "339830267",
            description = "Questo cafè è aperto tutti i giorni e oltre gli ospiti umani ospita tanti " +
                    "amici felini che terranno compagnia a tutti",
            latitude = 38.136949,
            longitude = 13.341157,
            rating = 5.0
        ),

        Place(
            image = R.drawable.cafe3,
            name = "Espresso",
            category = PlaceCategory.CAFE,
            address = "Via del Magnate, 96",
            phone = "341192629",
            description = "La catena Espresso offre ai propri clienti un caffè economico e veloce" +
                    " per le persone che vanno sempre di corsa",
            latitude = 38.136949,
            longitude = 13.341157,
            rating = 4.5
        ),

        Place(
            image = R.drawable.restaurant1,
            name = "Ristorante Chichi",
            category = PlaceCategory.RESTAURANT,
            address = "Via Leopardi, 88",
            phone = "385491756",
            description = "Il ristorante chichi è specializzato in pietanze e piatti raffinati " +
                    "per gli intenditori del settore",
            latitude = 38.136949,
            longitude = 13.341157,
            rating = 4.8
        ),

        Place(
            image = R.drawable.restaurant2,
            name = "La Masseria",
            category = PlaceCategory.RESTAURANT,
            address = "Via Du Monde, 11",
            phone = "3698445630",
            description = "Il nostro ristorante offre un ambiente rustico e accogliente per i clienti " +
                    " che sono sempre i benvenuti!",
            latitude = 38.136949,
            longitude = 13.341157,
            rating = 3.8
        ),

        Place(
            image = R.drawable.cinema1,
            name = "The Space Cinema",
            category = PlaceCategory.CINEMA,
            address = "Viale Antonio Bandiera, 32",
            phone = "354327366",
            description = "Il cinema più famoso della zona, per gli amanti del cinematografo.\nThe Space Cinema dispone al suo interno di 5 sale, arredate in stile moderno ",
            latitude = 38.136949,
            longitude = 13.341157,
            rating = 2.3
        ),

        Place(
            image = R.drawable.cinema2,
            name = "Cinema Revolution",
            category = PlaceCategory.CINEMA,
            address = "Via Dalmazia, 4",
            phone = "3324353001",
            description = "Film recenti, cineforum e spettacoli in uno spazio essenziale caratterizzato " +
                    "dal rosso di poltrone e tendaggi.",
            latitude = 38.136949,
            longitude = 13.341157,
            rating = 3.8
        ),

        Place(
            image = R.drawable.family1,
            name = "Luna Park Baby",
            category = PlaceCategory.FAMILY,
            address = "Via Rocco Tanica, 17",
            phone = "3901929923",
            description = "Storico luna park della zona, posto speciale per i bambini",
            latitude = 38.136949,
            longitude = 13.341157,
            rating = 4.2
        ),

        Place(
            image = R.drawable.park1,
            name = "Parco del Mercatello",
            category = PlaceCategory.PARK,
            address = "Via del Mercatello, 1",
            phone = null,
            description = "Ampio parco che offre distese verdi e un lago artificiale, oltre a " +
                    "un giardino di rocce in una serra con cactus.",
            latitude = 38.136949,
            longitude = 13.341157,
            rating = 4.2
        ),

        Place(
            image = R.drawable.park2,
            name = "Parco Pinocchio",
            category = PlaceCategory.PARK,
            address = "Piazza Montpellier, 27",
            phone = null,
            description = "Parco di quartiere con area giochi, anfiteatro, fontana, pergola e statua " +
                    "bronzea di Pinocchio.",
            latitude = 38.136949,
            longitude = 13.341157,
            rating = 3.8
        ),

        Place(
            image = R.drawable.pizzeria1,
            name = "Pizza Paradise",
            category = PlaceCategory.PIZZERIA,
            address = "Via Veglia, 25",
            phone = "3506605452",
            description = "La pizzeria Giagiù offre consumazione sul posto e servizio da asporto, " +
                    "passate a trovarci!",
            latitude = 38.136949,
            longitude = 13.341157,
            rating = 4.7
        ),

        Place(
            image = R.drawable.pizzeria2,
            name = "We Stuff Pizza",
            category = PlaceCategory.PIZZERIA,
            address = "Via Romoletti , 55",
            phone = "395169842",
            description = "Ampia scelta di pizze cotte a legna in una location curata con una sala " +
                    "intima e terrazza vista duomo.",
            latitude = 38.136949,
            longitude = 13.341157,
            rating = 4.5
        ),

        Place(
            image = R.drawable.pub1,
            name = "Crown",
            category = PlaceCategory.PUB,
            address = "Via Roma, 421",
            phone = "388805165",
            description = "Pub irlandese tradizionale con birre alla spina, cocktail e piatti informali " +
                    "in un ambiente confortevole e rustico.",
            latitude = 38.136949,
            longitude = 13.341157,
            rating = 4.6
        ),

        Place(
            image = R.drawable.pub2,
            name = "Royal Oak",
            category = PlaceCategory.PUB,
            address = "Via Roma, 143",
            phone = "3558938994",
            description = "Pizze e birre artigianali in un locale che riproduce un galeone con pirati " +
                    "in vetroceramica e arredi in legno.",
            latitude = 38.136949,
            longitude = 13.341157,
            rating = 3.8
        )
    ).sortedBy { it.name }

    val testCase1 = placesList.subList(0, 2)
    val testCase2 = placesList.filter { it.category == PlaceCategory.CAFE }
    val testCase3 = placesList.subList(0, 2)
}