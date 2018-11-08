package util;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;

public class ComponentLoad {

	private static List<Node> nodes = null;

	public static void resetSetup() {

		if (nodes == null) {
			nodes = new ArrayList<Node>();
		} else {
			for (int i = 0; i < nodes.size(); i++)
				SceneBuilder.removePaneComponent((Node) nodes.get(i));

			nodes.clear();
		}
	}

	public static void add(Node novo) {
		nodes.add(novo);
	}

}
