package dev.suhrob.kattabozor.data.mapper

import dev.suhrob.kattabozor.data.remote.dto.offers.AttributeDto
import dev.suhrob.kattabozor.data.remote.dto.offers.ImageDto
import dev.suhrob.kattabozor.data.remote.dto.offers.MainDto
import dev.suhrob.kattabozor.data.remote.dto.offers.OfferDto
import dev.suhrob.kattabozor.domain.model.MainModel
import dev.suhrob.kattabozor.domain.model.offers.AttributeModel
import dev.suhrob.kattabozor.domain.model.offers.ImageModel
import dev.suhrob.kattabozor.domain.model.offers.OfferModel

fun OfferDto.toModel(): OfferModel {
    return OfferModel(
        attributes = attributes.map { it.toModel() },
        brand = brand,
        category = category,
        id = id,
        image = image.toModel(),
        merchant = merchant,
        name = name
    )
}

fun ImageDto.toModel(): ImageModel {
    return ImageModel(height = height, url = url, width = width)
}

fun AttributeDto.toModel(): AttributeModel {
    return AttributeModel(name = name, value = value)
}

fun MainDto.toModel(): MainModel {
    return MainModel(offers = offers.map { it.toModel() })
}