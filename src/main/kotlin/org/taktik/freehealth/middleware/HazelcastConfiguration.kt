/*
 *
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of FreeHealthConnector.
 *
 * FreeHealthConnector is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation.
 *
 * FreeHealthConnector is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with FreeHealthConnector.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.taktik.freehealth.middleware

import com.hazelcast.config.Config
import com.hazelcast.config.EvictionConfig
import com.hazelcast.config.MapConfig
import com.hazelcast.core.HazelcastInstance
import com.hazelcast.map.IMap
import com.hazelcast.collection.ISet
import com.hazelcast.collection.ItemEvent
import com.hazelcast.collection.ItemListener
import com.hazelcast.config.EvictionPolicy
import com.hazelcast.config.MaxSizePolicy
import com.hazelcast.core.EntryEvent
import com.hazelcast.map.listener.EntryEvictedListener
import org.apache.commons.lang3.tuple.Triple
import org.apache.commons.logging.LogFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.taktik.connector.technical.service.etee.domain.EncryptionToken
import org.taktik.connector.technical.service.kgss.domain.SerializableKeyResult
import org.taktik.connector.technical.utils.IdentifierType
import org.taktik.freehealth.middleware.domain.sts.SamlTokenResult
import java.util.UUID

@Component
@ConfigurationProperties("icure.hazelcast")
class HazelcastProperties {
    var groupName : String? = null
    var groupPassword : String? = null
}

@Configuration
class HazelcastConfiguration(val hazelcastProperties: HazelcastProperties) {
    private val log = LogFactory.getLog(this::class.java)

    @Bean fun config() = Config().apply {
        hazelcastProperties.groupName?.let { this.clusterName = it }
        // groupPassword is not used in Hazelcast 5.x+ (removed)
        addMapConfig(MapConfig("ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.KEYSTORES").apply {
            timeToLiveSeconds = 18*3600
            asyncBackupCount = 1
            isReadBackupData = true
            evictionConfig = EvictionConfig()
                .setSize(128)
                .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
                .setEvictionPolicy(EvictionPolicy.LRU)
        })
        addMapConfig(MapConfig("ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.TOKENS").apply {
            timeToLiveSeconds = 12*3600
            asyncBackupCount = 1
            isReadBackupData = true
            evictionConfig = EvictionConfig()
                .setSize(128)
                .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
                .setEvictionPolicy(EvictionPolicy.LRU)
        })
        addMapConfig(MapConfig("ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.ETK").apply {
            timeToLiveSeconds = 8*3600
            asyncBackupCount = 1
            isReadBackupData = true
            evictionConfig = EvictionConfig()
                .setSize(128)
                .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
                .setEvictionPolicy(EvictionPolicy.LRU)
        })
        addMapConfig(MapConfig("ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.LONGLIVEDETK").apply {
            timeToLiveSeconds = 3*365*24*3600
            asyncBackupCount = 1
            isReadBackupData = true
            evictionConfig = EvictionConfig()
                .setSize(512)
                .setMaxSizePolicy(MaxSizePolicy.USED_HEAP_SIZE)
                .setEvictionPolicy(EvictionPolicy.LRU)
        })
        addMapConfig(MapConfig("ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.KGSS").apply {
            timeToLiveSeconds = 12*3600
            asyncBackupCount = 1
            isReadBackupData = true
            evictionConfig = EvictionConfig()
                .setSize(128)
                .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
                .setEvictionPolicy(EvictionPolicy.LRU)
        })
    }

    @Bean
    fun keystoresMap(hazelcastInstance: HazelcastInstance): IMap<UUID, ByteArray> {
        val map = hazelcastInstance.getMap<UUID, ByteArray>("ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.KEYSTORES").apply {
            this.addEntryListener(object : EntryEvictedListener<UUID, ByteArray> {
                override fun entryEvicted(event: EntryEvent<UUID, ByteArray>) {
                    log.info("Keystore ${event.key} evicted")
                }
            }, false)
        }
        return map
    }

    @Bean
    fun tokensMap(hazelcastInstance: HazelcastInstance): IMap<UUID, SamlTokenResult> {
        return hazelcastInstance.getMap<UUID, SamlTokenResult>("ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.TOKENS").apply {
            this.addEntryListener(object : EntryEvictedListener<UUID, SamlTokenResult> {
                override fun entryEvicted(event: EntryEvent<UUID, SamlTokenResult>) {
                    log.info("Token ${event.key} evicted")
                }
            }, false)
        }
    }

    @Bean
    fun keystoreConnections(hazelcastInstance: HazelcastInstance): ISet<UUID> {
        return hazelcastInstance.getSet<UUID>("ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.KEYSTORECONNECTIONS").apply {
            this.addItemListener(object : ItemListener<UUID> {
                override fun itemAdded(item: ItemEvent<UUID>) {}
                override fun itemRemoved(item: ItemEvent<UUID>) { log.info("Connection ${item.item} evicted") }
            }, false)
        }
    }

    @Bean
    fun etksMap(hazelcastInstance: HazelcastInstance): IMap<Triple<IdentifierType, String, String>, Set<EncryptionToken>> {
        return hazelcastInstance.getMap<Triple<IdentifierType, String, String>, Set<EncryptionToken>>("ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.ETK").apply {
            this.addEntryListener(object : EntryEvictedListener<Triple<IdentifierType, String, String>, Set<EncryptionToken>> {
                override fun entryEvicted(event: EntryEvent<Triple<IdentifierType, String, String>, Set<EncryptionToken>>) {
                    log.info("ETK ${event.key} evicted")
                }
            }, false)
        }
    }

    @Bean
    fun longLivedEtksMap(hazelcastInstance: HazelcastInstance): IMap<org.apache.commons.lang3.tuple.Pair<UUID, Triple<IdentifierType, String, String>>, Set<EncryptionToken>> {
        return hazelcastInstance.getMap<org.apache.commons.lang3.tuple.Pair<UUID, Triple<IdentifierType, String, String>>, Set<EncryptionToken>>("ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.LONGLIVEDETK").apply {
            this.addEntryListener(object : EntryEvictedListener<org.apache.commons.lang3.tuple.Pair<UUID, Triple<IdentifierType, String, String>>, Set<EncryptionToken>> {
                override fun entryEvicted(event: EntryEvent<org.apache.commons.lang3.tuple.Pair<UUID, Triple<IdentifierType, String, String>>, Set<EncryptionToken>>) {
                    log.info("ETK ${event.key} evicted")
                }
            }, false)
        }
    }

    @Bean
    fun kgssMap(hazelcastInstance: HazelcastInstance): IMap<UUID, SerializableKeyResult> {
        return hazelcastInstance.getMap<UUID, SerializableKeyResult>("ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.KGSS").apply {
            this.addEntryListener(object : EntryEvictedListener<UUID, SerializableKeyResult> {
                override fun entryEvicted(event: EntryEvent<UUID, SerializableKeyResult>) {
                    log.info("KGSS ${event.key} evicted")
                }
            }, false)
        }
    }
}
