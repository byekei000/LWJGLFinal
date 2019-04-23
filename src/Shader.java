import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

public abstract class Shader {
    private int vertexShaderID, fragmentShaderID, programID;
    private String fragmentFile, vertexFile;
    public Shader(String vertexFile, String fragmentFile){
        this.vertexFile = vertexFile;
        this.fragmentFile = fragmentFile;
    }

    public void create(){
        programID = GL20.glCreateProgram();

        vertexShaderID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        GL20.glShaderSource(vertexShaderID, readFile(vertexFile));
        GL20.glCompileShader(vertexShaderID);

        if(GL20.glGetShaderi(vertexShaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE){
            System.err.println("Error: Vertex Shader - " + GL20.glGetShaderInfoLog(vertexShaderID));
        }

        fragmentShaderID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL20.glShaderSource(fragmentShaderID, readFile(fragmentFile));
        GL20.glCompileShader(fragmentShaderID);

        if(GL20.glGetShaderi(fragmentShaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE){
            System.err.println("Error: Fragment Shader - " + GL20.glGetShaderInfoLog(fragmentShaderID));
        }

        GL20.glAttachShader(programID, vertexShaderID);
        GL20.glAttachShader(programID, fragmentShaderID);

        GL20.glLinkProgram(programID);

        if(GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == GL11.GL_FALSE){
            System.err.println("Error: Program Linking - " + GL20.glGetShaderInfoLog(programID));
        }

        GL20.glValidateProgram(programID);

        if(GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE){
            System.err.println("Error: Program Validating - " + GL20.glGetShaderInfoLog(programID));
        }
    }

    public void bind(){
        GL20.glUseProgram(programID);
    }

    public void remove(){
        GL20.glUseProgram(0);
        GL20.glDetachShader(programID, vertexShaderID);
        GL20.glDetachShader(programID, fragmentShaderID);
        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
        GL20.glDeleteProgram(programID);
    }

    public abstract void bindAllAttributes();

    public void bindAttribute(int attribute, String variableName){
        GL20.glBindAttribLocation(programID, attribute, variableName);
    }

    protected abstract void getAllUniforms();

    protected int getUniform(String name){
        return GL20.glGetUniformLocation(programID, name);
    }

    protected void loadFloatUniform(int location, float value){
        GL20.glUniform1f(location, value);
    }

    protected void loadIntUniform(int location, float value){
        GL20.glUniform1f(location, value);
    }

    protected void loadVectorUniform(int location, Vector3f value){
        GL20.glUniform3f(location, value.x, value.y, value.z);
    }

    protected void loadMatrixUniform(int location, Matrix4f value){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
        value.get(buffer);
        GL20.glUniformMatrix4fv(location, false, buffer);
    }

    private String readFile(String file){
        StringBuilder string = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine()) != null){
                string.append(line).append("\n");
            }
            reader.close();
        }catch(IOException e){
            System.err.println("Error: Couldn't find file");
            System.exit(-1);
        }
        return string.toString();
    }
}
