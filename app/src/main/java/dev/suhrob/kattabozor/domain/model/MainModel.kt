package dev.suhrob.kattabozor.domain.model

import dev.suhrob.kattabozor.domain.model.offers.OfferModel

data class MainModel(
    val offers: List<OfferModel>
)