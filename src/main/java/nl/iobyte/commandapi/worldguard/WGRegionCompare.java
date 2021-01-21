package nl.iobyte.commandapi.worldguard;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import java.util.Comparator;

public class WGRegionCompare implements Comparator<ProtectedRegion> {

    @Override
    public int compare(ProtectedRegion o1, ProtectedRegion o2) {
        if (o1.getPriority() == o2.getPriority()) {
            return 0;
        } else if (o1.getPriority() < o2.getPriority()) {
            return 1;
        }

        return -1;
    }

}