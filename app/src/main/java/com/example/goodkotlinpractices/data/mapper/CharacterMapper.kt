package com.example.goodkotlinpractices.data.mapper

import com.example.goodkotlinpractices.data.datasource.local.room.entities.CharacterEntity
import com.example.goodkotlinpractices.data.response.rick.ResponseCharacter
import com.example.goodkotlinpractices.domain.models.CharacterModel

// Extension function to convert ResponseCharacter to CharacterModel
fun ResponseCharacter.toDomainModel(): CharacterModel {
    return CharacterModel(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        image = this.image
    )
}

// Extension function to convert CharacterModel to ResponseCharacter
fun CharacterModel.toResponseModel(): ResponseCharacter {
    return ResponseCharacter(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        image = this.image
    )
}

// Extension function to convert ResponseCharacter to CharacterEntity
fun CharacterModel.toEntityModel(): CharacterEntity {
    return CharacterEntity(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        image = this.image
    )
}

// Extension function to convert CharacterEntity to CharacterModel
fun CharacterEntity.toDomainModel(): CharacterModel {
    return CharacterModel(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        image = "" // Consider handling the image properly if necessary
    )
}
