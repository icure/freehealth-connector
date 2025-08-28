package org.taktik.connector.business.therlink.domain.requests

import java.io.Serializable
import org.apache.commons.lang3.ArrayUtils
import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder
import org.apache.commons.lang3.builder.ToStringBuilder

class BinaryProof(var method: String?, binary: ByteArray) : Serializable {
    var binary: ByteArray? = null
        get() = field?.clone()
        set(value) {
            field = ArrayUtils.clone(value)
        }

    init {
        this.binary = ArrayUtils.clone(binary)
    }

    override fun hashCode(): Int {
        val builder = HashCodeBuilder()
        builder.append(this.binary)
        builder.append(this.method)
        return builder.toHashCode()
    }

    override fun equals(obj: Any?): Boolean {
        if (obj == null) {
            return false
        } else if (obj !is BinaryProof) {
            return false
        } else if (obj === this) {
            return true
        } else {
            val other = obj as BinaryProof?
            val builder = EqualsBuilder()
            builder.append(this.binary, other!!.binary)
            builder.append(this.method, other.method)
            return builder.isEquals
        }
    }

    override fun toString(): String {
        val builder = ToStringBuilder(this)
        builder.append(this.binary)
        builder.append(this.method)
        return builder.toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
