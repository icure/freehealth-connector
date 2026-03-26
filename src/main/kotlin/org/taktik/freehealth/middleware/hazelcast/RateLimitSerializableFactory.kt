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

package org.taktik.freehealth.middleware.hazelcast

import com.hazelcast.nio.serialization.DataSerializableFactory
import com.hazelcast.nio.serialization.IdentifiedDataSerializable
import org.taktik.freehealth.middleware.domain.RateLimitEntry
import org.taktik.freehealth.middleware.domain.RateLimitResult
import org.taktik.freehealth.middleware.domain.RateLimitSerializableConstants

class RateLimitSerializableFactory : DataSerializableFactory {
    override fun create(typeId: Int): IdentifiedDataSerializable? {
        return when (typeId) {
            RateLimitSerializableConstants.RATE_LIMIT_ENTRY_CLASS_ID -> RateLimitEntry()
            RateLimitSerializableConstants.RATE_LIMIT_RESULT_CLASS_ID -> RateLimitResult()
            RateLimitSerializableConstants.RATE_LIMIT_ENTRY_PROCESSOR_CLASS_ID -> RateLimitEntryProcessor()
            else -> null
        }
    }
}