package sample;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.*;
import javax.swing.Timer;
import javax.vecmath.*;

public class ComplexObject implements ActionListener {
    private TransformGroup treeTransformGroup;
    private Transform3D treeTransform3D = new Transform3D();
    private Timer timer;
    private float angle = 0;
    public static void main(String[] args) {
        new ComplexObject();
    }

    public ComplexObject() {
        timer = new Timer(50, this);
        timer.start();
        BranchGroup scene = createSceneGraph();
        SimpleUniverse u = new SimpleUniverse();
        u.getViewingPlatform().setNominalViewingTransform();
        u.addBranchGraph(scene);
    }
    public BranchGroup createSceneGraph() {
// створюємо групу об'єктів
        BranchGroup objRoot = new BranchGroup();
// створюємо об'єкт, що будемо додавати до групи
        treeTransformGroup = new TransformGroup();
        treeTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        buildTree();
        objRoot.addChild(treeTransformGroup);
// налаштовуємо освітлення
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),100.0);
        Color3f light1Color = new Color3f(1.0f, 0.5f, 0.4f);
        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight light1 = new DirectionalLight(light1Color,
                light1Direction);
        light1.setInfluencingBounds(bounds);
        objRoot.addChild(light1);
// встановлюємо навколишнє освітлення
        Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
        AmbientLight ambientLightNode = new AmbientLight(ambientColor);
        ambientLightNode.setInfluencingBounds(bounds);
        objRoot.addChild(ambientLightNode);
        return objRoot;
    }
    private void buildTree() {
        TransformGroup tgTop = new TransformGroup();
        Transform3D transformTop = new Transform3D();
        Box coneTop = XMassRobo.getCone(0.1f, 0.15f, 0.12f);
        Vector3f vectorTop = new Vector3f(.0f, 0.2f, .0f);
        transformTop.setTranslation(vectorTop);
        tgTop.setTransform(transformTop);
        tgTop.addChild(coneTop);
        treeTransformGroup.addChild(tgTop);

        TransformGroup tgMiddle = new TransformGroup();
        Transform3D transformMiddle = new Transform3D();
        Box coneMiddle = XMassRobo.getCone(0.2f, 0.1f, 0.1f);
        Vector3f vectorMiddle = new Vector3f(.0f, .0f, .0f);
        transformMiddle.setTranslation(vectorMiddle);
        tgMiddle.setTransform(transformMiddle);
        tgMiddle.addChild(coneMiddle);
        treeTransformGroup.addChild(tgMiddle);
//
        TransformGroup tgBottomLeft = new TransformGroup();
        Transform3D transformBottomLeft = new Transform3D();
        Box coneBottomLeft = XMassRobo.getCone(0.05f, 0.05f, 0.35f);
        Vector3f vectorBottomLeft = new Vector3f(0.0f, -0.2f, 0.1f);
        transformBottomLeft.setTranslation(vectorBottomLeft);
        tgBottomLeft.setTransform(transformBottomLeft);
        tgBottomLeft.addChild(coneBottomLeft);
        treeTransformGroup.addChild(tgBottomLeft);

        TransformGroup tgBottomRight = new TransformGroup();
        Transform3D transformBottomRight = new Transform3D();
        Box coneBottomRight = XMassRobo.getCone(0.05f, 0.05f, 0.35f);
        Vector3f vectorBottomRight = new Vector3f(.0f, -0.2f, -0.1f);
        transformBottomRight.setTranslation(vectorBottomRight);
        tgBottomRight.setTransform(transformBottomRight);
        tgBottomRight.addChild(coneBottomRight);
        treeTransformGroup.addChild(tgBottomRight);

        TransformGroup tgMidRight = new TransformGroup();
        Transform3D transformMidRight = new Transform3D();
        Box coneMidRight = XMassRobo.getCone(0.05f, 0.05f, 0.25f);
        Vector3f vectorMidRight = new Vector3f(.0f, -0.155f, 0.25f);
        transformMidRight.setTranslation(vectorMidRight);
        tgMidRight.setTransform(transformMidRight);
        tgMidRight.addChild(coneMidRight);
        treeTransformGroup.addChild(tgMidRight);

        TransformGroup tgMidRight2 = new TransformGroup();
        Transform3D transformMidRight2 = new Transform3D();
        Box coneMidRight2 = XMassRobo.getCone(0.05f, 0.05f, 0.25f);
        Vector3f vectorMidRight2 = new Vector3f(0.0f, -0.155f, -0.25f);
        transformMidRight2.setTranslation(vectorMidRight2);
        tgMidRight2.setTransform(transformMidRight2);
        tgMidRight2.addChild(coneMidRight2);
        treeTransformGroup.addChild(tgMidRight2);

        createBall(0.03f, 0.17f, 0.22f, 0.05f, "", new Color3f(0.6f, 0.6f, 0.0f));
        createBall(0.03f, 0.17f, 0.22f, -0.05f, "", new Color3f(0.6f, 0.6f, 0.0f));
        createBall(0.09f, 0f, -0.15f, -0.25f, "", new Color3f(0.6f, 0.6f, 0.0f));
        createBall(0.09f, 0f, -0.15f, 0.25f, "", new Color3f(0.6f, 0.6f, 0.0f));
        createBall(0.07f, 0.03f, -0.35f, 0.1f, "", new Color3f(0.6f, 0.6f, 0.0f));
        createBall(0.07f, 0.03f, -0.35f, -0.1f, "", new Color3f(0.6f, 0.6f, 0.0f));
    }
    private void createBall(float radius, float x, float y, float z, String picture,
                            Color3f emissive) {
        TransformGroup tg = new TransformGroup();
        Transform3D transform = new Transform3D();
        Sphere cone = XMassBall.getSphere(radius, picture, emissive);
        Vector3f vector = new Vector3f(x, y, z);
        transform.setTranslation(vector);
        tg.setTransform(transform);
        tg.addChild(cone);
        treeTransformGroup.addChild(tg);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        treeTransform3D.rotY(angle);
        treeTransformGroup.setTransform(treeTransform3D);
        angle += 0.05;
    }
}