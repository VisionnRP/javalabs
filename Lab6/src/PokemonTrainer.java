import javax.vecmath.*;

import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.behaviors.vp.*;
import javax.swing.JFrame;
import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.objectfile.*;
import java.util.Hashtable;

public class PokemonTrainer extends JFrame{
    public Canvas3D myCanvas3D;

    public PokemonTrainer(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myCanvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        SimpleUniverse simpUniv = new SimpleUniverse(myCanvas3D);
        simpUniv.getViewingPlatform().setNominalViewingTransform();
        createSceneGraph(simpUniv);
        defaultLight(simpUniv);
        OrbitBehavior ob = new OrbitBehavior(myCanvas3D);
        ob.setSchedulingBounds(new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE));
        simpUniv.getViewingPlatform().setViewPlatformBehavior(ob);
        setTitle("Pokemon Trainer");
        setSize(700,700);
        getContentPane().add("Center", myCanvas3D);
        setVisible(true);
    }
    public void createSceneGraph(SimpleUniverse su){
        ObjectFile f = new ObjectFile(ObjectFile.RESIZE);
        BoundingSphere bs = new BoundingSphere(new Point3d(0.0,0.0,0.0),6);
        BranchGroup trainerBranchGroup = new BranchGroup();
        TextureLoader t = new TextureLoader("sf//backg.png", myCanvas3D);
        Background trainerBackground =  new Background(t.getImage());
        Scene trainerScene = null;
        try{
            trainerScene = f.load("sf//pokemon_trainer.obj");
        }
        catch (Exception e){
            System.out.println(e);
        }
        Hashtable rchn = trainerScene.getNamedObjects();
        Transform3D startT = new Transform3D();
        startT.setScale(2.0/4);
        Transform3D cmb = new Transform3D();
        cmb.mul(startT);
        TransformGroup sstfg = new TransformGroup(cmb);
        Appearance HEAD = new Appearance();
        setDefault(HEAD, new Color3f(0.4f, 0.6f, 0.4f));
        Shape3D head = (Shape3D) rchn.get("polygon1");
        head.setAppearance(HEAD);
        TransformGroup headTransform = new TransformGroup();
        headTransform.addChild(head.cloneTree());
        Transform3D ax1 = new Transform3D();
        ax1.set(new Vector3d(0.0, 0.0, 0.0));
        headTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Appearance ballpp = new Appearance();
        setDefault(ballpp, new Color3f(0.6f, 0.4f, 0.4f));
        Alpha ballpa = new Alpha(100, 1, 0, 100, 5000,50000,5,50000,5,50000);
        Shape3D ball = (Shape3D) rchn.get("ball1");
        ball.setAppearance(ballpp);
        TransformGroup blg = new TransformGroup();
        blg.addChild(ball.cloneTree());
        Transform3D blrg = new Transform3D();
        RotationInterpolator ballrot = new RotationInterpolator(ballpa, blg, blrg, 0.0f, (float) 0.5);
        ballrot.setSchedulingBounds(bs);
        blg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        blg.addChild(ballrot);
        Appearance bmapp = new Appearance();
        setDefault(bmapp, new Color3f(0.4f, 0.7f, 0.4f));
        TransformGroup sg = new TransformGroup();
        sg.addChild(headTransform);
        sg.addChild(blg);
        TransformGroup tbf6 = new TransformGroup();
        Shape3D fds = (Shape3D) rchn.get("polygon0");
        fds.setAppearance(bmapp);
        tbf6.addChild(fds.cloneTree());
        sg.addChild(tbf6.cloneTree());
        Appearance faceApp = new Appearance();
        setDefault(faceApp, new Color3f(0.3f, 0.3f, 0.2f));

        TransformGroup tgf1 = new TransformGroup();
        Shape3D fcshape = (Shape3D) rchn.get("polygon3");
        fcshape.setAppearance(faceApp);
        tgf1.addChild(fcshape.cloneTree());
        sg.addChild(tgf1.cloneTree());
        TransformGroup tgr2 = new TransformGroup();
        Shape3D tgrbd = (Shape3D) rchn.get("polygon4");
        tgrbd.setAppearance(faceApp);
        tgr2.addChild(tgrbd.cloneTree());
        sg.addChild(tgr2.cloneTree());
        TransformGroup tlcs = new TransformGroup();
        Shape3D zxcvv = (Shape3D) rchn.get("polygon2");
        zxcvv.setAppearance(faceApp);
        tlcs.addChild(zxcvv.cloneTree());
        sg.addChild(tlcs.cloneTree());
        Appearance bintttt = new Appearance();
        setDefault(bintttt, new Color3f(0.0f, 0.6f, 0.0f));
        TransformGroup tgBag1 = new TransformGroup();
        Shape3D bsru = (Shape3D) rchn.get("polygon6");
        bsru.setAppearance(bintttt);
        tgBag1.addChild(bsru.cloneTree());
        sg.addChild(tgBag1.cloneTree());
        TransformGroup tgBag = new TransformGroup();
        Shape3D bandit = (Shape3D) rchn.get("polygon7");
        bandit.setAppearance(bintttt);
        tgBag.addChild(bandit.cloneTree());
        sg.addChild(tgBag.cloneTree());
        TransformGroup entries = stepper(getExistens(
        sstfg,
        new Vector3f(0.0f,0.0f,0.0f)), new Alpha(1,1));
        trainerBranchGroup.addChild(entries);
        sstfg.addChild(sg);
        BoundingSphere bounds = new BoundingSphere(new Point3d(240.0,250.0,200.0),Double.MAX_VALUE);
        trainerBackground.setApplicationBounds(bounds);
        trainerBranchGroup.addChild(trainerBackground);
        trainerBranchGroup.compile();
        su.addBranchGraph(trainerBranchGroup);
    }
    public void defaultLight(SimpleUniverse su){
        // default value for light
        BranchGroup bgLight = new BranchGroup();
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
        Color3f lightColour1 = new Color3f(1.0f,1.0f,1.0f);
        Vector3f lightDir1 = new Vector3f(-1.0f,0.0f,-0.5f);
        DirectionalLight light1 = new DirectionalLight(lightColour1, lightDir1);
        light1.setInfluencingBounds(bounds);
        bgLight.addChild(light1);
        su.addBranchGraph(bgLight);
    }
    private TransformGroup getExistens(Node node, Vector3f vector){
        Transform3D useAvailable3dOptions = new Transform3D();
        useAvailable3dOptions.setTranslation(vector);
        TransformGroup transformGroup = new TransformGroup();
        transformGroup.setTransform(useAvailable3dOptions);
        transformGroup.addChild(node);
        return transformGroup;
    }
    private TransformGroup stepper(Node node, Alpha alpha){
        TransformGroup xformGroup = new TransformGroup();
        xformGroup.setCapability(
                TransformGroup.ALLOW_TRANSFORM_WRITE);
        RotationInterpolator interpolator =
                new RotationInterpolator(alpha,xformGroup);
        interpolator.setSchedulingBounds(new BoundingSphere(
                new Point3d(5.0,10.0,100.0),5.0));
        xformGroup.addChild(interpolator);
        xformGroup.addChild(node);
        return xformGroup;
    }
    public static void setDefault(Appearance app, Color3f col) {
        app.setMaterial(new Material(col, col, col, col, 150.0f));
    }
    public static void main(String[] args) {
        new PokemonTrainer();
    }
    
}

