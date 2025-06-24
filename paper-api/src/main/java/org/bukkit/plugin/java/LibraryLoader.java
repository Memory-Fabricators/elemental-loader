package org.bukkit.plugin.java;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.plugin.PluginDescriptionFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// Paper start
@org.jetbrains.annotations.ApiStatus.Internal
public class LibraryLoader {
// Paper end

    private final Logger logger;
    public static java.util.function.BiFunction<URL[], ClassLoader, URLClassLoader> LIBRARY_LOADER_FACTORY; // Paper - rewrite reflection in libraries
    public static java.util.function.Function<List<java.nio.file.Path>, List<java.nio.file.Path>> REMAPPER; // Paper - remap libraries

    public LibraryLoader(@NotNull Logger logger) {
        this.logger = logger;
    }

    @Nullable
    public ClassLoader createLoader(@NotNull PluginDescriptionFile desc) {
        // Paper start - plugin loader api
        return this.createLoader(desc, null);
    }

    @Nullable
    public ClassLoader createLoader(@NotNull PluginDescriptionFile desc, java.util.@Nullable List<java.nio.file.Path> paperLibraryPaths) {
        return null;
    }
}
