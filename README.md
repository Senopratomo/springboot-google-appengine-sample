<h2>Simple REST API with Spring Boot and deploy in Google App Engine Standard JAVA 8 + Cloud Datastore</h2>

<p>This is a simple project I use as reference / starter when building Spring Boot
app engine project with Cloud Datastore backend</p>

<p>To deploy the app:</p>
<ol>
<li>clone this repo</li>
<li>run 'mvn clean package'</li>
<li>run 'gcloud init' and follow instruction to setup your
project</li>
<li>run 'mvn appengine:deploy' to deploy this app to your project</li>
<li> Enjoy </li>
</ol>

<p>To launch the app locally: </p>
<ol>
<li> Follow step 1 above</li>
<li> Open 'src/main/java/org.senolab.springbootappenginedatastoredemo/SpringbootAppengineDatastoreDemoApplication.java</li>
<li> Uncomment the method cloudDatastoreService() and set the project id within the code </li>
<li> run 'mvn clean package' </li>
<li> run 'mvn appengine:run' to deploy this app locally </li>
<li> Access the app in localhost:8080 </li>
</ol>

