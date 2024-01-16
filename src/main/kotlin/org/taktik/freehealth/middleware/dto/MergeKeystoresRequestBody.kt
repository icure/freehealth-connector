package org.taktik.freehealth.middleware.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable

/**
 * @author Bram Swinnen
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class MergeKeystoresRequestBody(var newKeystore: String, var oldKeystore: String, var newPassword: String, var oldPassword: String) : Serializable {

}
