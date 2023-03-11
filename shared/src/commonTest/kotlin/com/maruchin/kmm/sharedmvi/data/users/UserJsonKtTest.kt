package com.maruchin.kmm.sharedmvi.data.users

import com.maruchin.kmm.sharedmvi.sampleUsers
import com.maruchin.kmm.sharedmvi.sampleUsersJson
import kotlin.test.Test
import kotlin.test.assertEquals

class UserJsonKtTest {

    @Test
    fun `Json as model`() {
        // when
        val model = sampleUsersJson[0].asModel()

        // then
        assertEquals(sampleUsers[0], model)
    }
}
