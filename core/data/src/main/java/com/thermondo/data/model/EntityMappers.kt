package com.thermondo.data.model

import com.thermondo.database.model.bookmark.BookmarkEntity
import com.thermondo.database.model.launch.*
import com.thermondo.model.data.Bookmark
import com.thermondo.network.model.*

fun NetworkCore.asEntity() = CoreEntity(
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

fun List<NetworkCore>.asCoreEntity() = this.map { it.asEntity() }

fun NetworkCrew.asEntity() = CrewEntity(crew = crew, role = role)
fun List<NetworkCrew>.asCrewEntities() = this.map { it.asEntity() }

fun NetworkFailure.asEntity() = FailureEntity(altitude = altitude, reason = reason, time = time)
fun List<NetworkFailure>.asFailureEntities() = this.map { it.asEntity() }

fun NetworkFairings.asEntity() = FairingsEntity(
    recovered = recovered, recoveryAttempt = recoveryAttempt, reused = reused, ships = ships
)

fun NetworkFlickr.asEntity() = FlickrEntity(original = original, small = small)

fun NetworkPatch.asEntity() = PatchEntity(large = large, small = small)

fun NetworkReddit.asEntity() = RedditEntity(
    campaign = campaign, launch = launch, media = media, recovery = recovery
)

fun NetworkLinks.asEntity() = LinksEntity(
    article = article,
    flickr = flickr.asEntity(),
    patch = patch.asEntity(),
    presskit = presskit,
    reddit = reddit.asEntity(),
    webcast = webcast,
    wikipedia = wikipedia,
    youtubeId = youtubeId

)

fun NetworkLaunch.asEntity() = LaunchEntity(
    id = id,
    autoUpdate = autoUpdate,
    capsules = capsules,
    cores = cores.asCoreEntity(),
    crew = crew.asCrewEntities(),
    dateLocal = dateLocal,
    datePrecision = datePrecision,
    dateUnix = dateUnix,
    dateUtc = dateUtc,
    details = details,
    failures = failures.asFailureEntities(),
    fairings = fairings?.asEntity(),
    flightNumber = flightNumber,
    launchLibraryId = launchLibraryId,
    launchpad = launchpad,
    links = links.asEntity(),
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


fun BookmarkEntity.asBookmark() = Bookmark(launchId = launchId)