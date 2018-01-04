package local;

/**
 * Created by Wienio on 2018-01-03.
 */
public class GaussIntegralCoords {

    public static final LocalNode[] gaussNodesCoords = {
            new LocalNode(-1 / Math.sqrt(3), -1 / Math.sqrt(3)),
            new LocalNode(1 / Math.sqrt(3), -1 / Math.sqrt(3)),
            new LocalNode(1 / Math.sqrt(3), 1 / Math.sqrt(3)),
            new LocalNode(-1 / Math.sqrt(3), 1 / Math.sqrt(3))
    };

    public static final LocalSurface[] gaussSurfaceCoords = {
            new LocalSurface(new LocalNode(-1, 1 / Math.sqrt(3)), new LocalNode(-1, -1 / Math.sqrt(3))),
            new LocalSurface(new LocalNode(-1 / Math.sqrt(3), -1), new LocalNode(1 / Math.sqrt(3), -1)),
            new LocalSurface(new LocalNode(1, -1 / Math.sqrt(3)), new LocalNode(1, 1 / Math.sqrt(3))),
            new LocalSurface(new LocalNode(1 / Math.sqrt(3), 1), new LocalNode(-1 / Math.sqrt(3), 1))
    };

}
