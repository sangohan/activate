package net.fwbrasil.activate.multipleVms

import net.fwbrasil.activate.mongoContext
import net.fwbrasil.activate.StoppableActivateContext
import net.fwbrasil.activate.entity.Entity
import net.fwbrasil.activate.coordinator.Coordinator

trait MultiVMContext extends StoppableActivateContext {

    Coordinator.timeout = 1000

    class IntEntity extends Entity {
        var intValue = 0
    }

    val storage = mongoContext.storage

    def run[A](f: => A) = {
        start
        try
            transactional(f)
        finally
            stop
    }

}

object ctx1 extends MultiVMContext
object ctx2 extends MultiVMContext