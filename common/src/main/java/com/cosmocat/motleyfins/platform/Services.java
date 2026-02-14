package com.cosmocat.motleyfins.platform;

import com.cosmocat.motleyfins.MotleyFins;
import com.cosmocat.motleyfins.platform.services.IPlatformHelper;
import java.util.ServiceLoader;

public class Services {

    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

    public static <T> T load(Class<T> clazz) {

        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        MotleyFins.LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }

}