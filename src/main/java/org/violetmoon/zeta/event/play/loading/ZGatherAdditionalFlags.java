package org.violetmoon.zeta.event.play.loading;

import org.violetmoon.quark.base.module.config.ConfigFlagManager;
import org.violetmoon.zeta.event.bus.IZetaPlayEvent;

public record ZGatherAdditionalFlags(ConfigFlagManager flagManager) implements IZetaPlayEvent { }
