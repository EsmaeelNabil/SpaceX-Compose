package com.thermondo.data.model

import com.thermondo.database.model.launch.*
import com.thermondo.model.data.*

fun CoreEntity.asExternalModel() = Core(
    core = core,
    flight = flight,
    gridfins = gridfins,
    landingAttempt = landingAttempt,
    landingSuccess = landingSuccess,
    landingType = landingType,
    landpad = landpad,
    legs = legs,
    reused = reused
)

fun List<CoreEntity>.asCoreExternalModel() = this.map { it.asExternalModel() }

fun CrewEntity.asExternalModel() = Crew(crew = crew, role = role)
fun List<CrewEntity>.asCrewExternalModel() = this.map { it.asExternalModel() }

fun FailureEntity.asExternalModel() = Failure(altitude = altitude, reason = reason, time = time)
fun List<FailureEntity>.asFailureExternalModels() = this.map { it.asExternalModel() }

fun FairingsEntity.asExternalModel() = Fairings(
    recovered = recovered, recoveryAttempt = recoveryAttempt, reused = reused, ships = ships
)

fun FlickrEntity.asExternalModel() = Flickr(original = original, small = small)

fun PatchEntity.asExternalModel() = Patch(large = large, small = small)

fun RedditEntity.asExternalModel() = Reddit(
    campaign = campaign, launch = launch, media = media, recovery = recovery
)

fun LinksEntity.asExternalModel() = Links(
    article = article,
    flickr = flickr.asExternalModel(),
    patch = patch.asExternalModel(),
    presskit = presskit,
    reddit = reddit.asExternalModel(),
    webcast = webcast,
    wikipedia = wikipedia,
    youtubeId = youtubeId

)

fun LaunchEntity.asExternalModel() = Launch(
    id = id,
    autoUpdate = autoUpdate,
    capsules = capsules,
    cores = cores.asCoreExternalModel(),
    crew = crew.asCrewExternalModel(),
    dateLocal = dateLocal,
    datePrecision = datePrecision,
    dateUnix = dateUnix,
    dateUtc = dateUtc,
    details = details,
    failures = failures.asFailureExternalModels(),
    fairings = fairings?.asExternalModel(),
    flightNumber = flightNumber,
    launchLibraryId = launchLibraryId,
    launchpad = launchpad,
    links = links.asExternalModel(),
    name = name,
    net = net,
    payloads = payloads,
    rocket = rocket,
    ships = ships,
    staticFireDateUnix = staticFireDateUnix,
    staticFireDateUtc = staticFireDateUtc,
    success = success,
    tbd = tbd,
    upcoming = upcoming,
    window = window
)