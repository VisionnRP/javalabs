package labs.lab5;

import com.sun.j3d.loaders.Loader;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.lw3d.Lw3dLoader;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Main extends JFrame {
	static SimpleUniverse universe;
	static Canvas3D canvas;
	static View theView;
	static BranchGroup sceneRoot;
	static TransformGroup mainTransformGroup;
	static Transform3D mainTransform3D;
	static double angle = 0;
	static int rotate = 0;

	Main() {
		canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		init();
		add(canvas);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		canvas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					rotate = -1;
				} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					rotate = +1;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT && rotate == -1) {
					rotate = 0;
				} else if(e.getKeyCode() == KeyEvent.VK_RIGHT && rotate == +1) {
					rotate = 0;
				}
			}
		});
		new Timer(16, (e) -> {
			angle += 0.005 * rotate;
			mainTransform3D.rotZ(angle);
			mainTransformGroup.setTransform(mainTransform3D);
		}).start();


	}

	public Node initBackground() {
		TextureLoader t = new TextureLoader("water.png", canvas);
		Background background = new Background(t.getImage());
		background.setImageScaleMode(Background.SCALE_FIT_ALL);
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 100.0,
				0.0),200.0);
		background.setApplicationBounds(bounds);
		return background;
	}

	public void init() {
		Loader lw3dLoader = new Lw3dLoader(Loader.LOAD_ALL);
		Scene loaderScene = null;

		try {
			loaderScene = lw3dLoader.load("start.lws");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		sceneRoot = new BranchGroup();

		universe = new SimpleUniverse(canvas);
		theView = universe.getViewer().getView();
		theView.setBackClipDistance(50000f);


		if (loaderScene.getSceneGroup() != null) {
			TransformGroup viewGroups[] = loaderScene.getViewGroups();

			Transform3D t = new Transform3D();
			viewGroups[0].getTransform(t);
			Matrix4d m = new Matrix4d();
			t.get(m);
			m.invert();
			t.set(m);

			TransformGroup tg = new TransformGroup(t);
			mainTransform3D = new Transform3D();

			tg.addChild(loaderScene.getSceneGroup());
			mainTransformGroup = new TransformGroup(mainTransform3D);
			mainTransformGroup.addChild(initBackground());
			mainTransformGroup.addChild(tg);
			mainTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

			sceneRoot.addChild(mainTransformGroup);
		}

		universe.addBranchGraph(sceneRoot);
	}

	public static void main(String[] args) {
		try {
			new Main();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(2);
		}
	}
}
