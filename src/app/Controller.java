package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Controller implements Initializable{

    @FXML
    private TextField patComum;
    @FXML
    private TextField patMaxpar;
    @FXML
    private TextArea scriptComum;
    @FXML
    private TextArea scriptMaxpar;
    @FXML
    private CheckBox zipComum;
    @FXML
    private CheckBox zipMaxpar;
    @FXML
    private RadioButton consultaComumRadio;
    @FXML
    private RadioButton correcaoComumRadio;
    @FXML
    private RadioButton consultaMaxparRadio;
    @FXML
    private RadioButton dadosMaxparRadio;
    @FXML
    private RadioButton estruturaMaxparRadio;

    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private FileChooser fileChooser = new FileChooser();
    private File file;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void padronizaComum(ActionEvent event) throws IOException {
        if(!(patComum.getText().isEmpty() || scriptComum.getText().isEmpty())){
            if(consultaComumRadio.isSelected()){
                salvaArquivoComum();
                escritorConsultaComum();

            }else if(correcaoComumRadio.isSelected()){
                salvaArquivoComum();
                escritorCorrecaoComum();
            }else{
                alert.setTitle("Importante");
                alert.setHeaderText(null);
                alert.setContentText("Selecione o escopo.");
                alert.showAndWait();
            }
        }else{
            alert.setTitle("Importante");
            alert.setHeaderText(null);
            alert.setContentText("Ainda existem campos obrigatórios não preenchidos.");
            alert.showAndWait();
        }

        if(zipComum.isSelected()){
            zipaArquivo();
        }
    }


    @FXML
    public void padronizaMaxpar(ActionEvent event) throws IOException {
        if(!(patMaxpar.getText().isEmpty() || scriptMaxpar.getText().isEmpty())){
            if(consultaMaxparRadio.isSelected()){
                salvaArquivoMaxpar();
                escritorConsultaMaxpar();
            }else if(dadosMaxparRadio.isSelected()){
                salvaArquivoMaxpar();
                escritorDadosMaxpar();
            }else if(estruturaMaxparRadio.isSelected()){
                salvaArquivoMaxpar();
                escritorEstruturaMaxpar();
            }else{
                alert.setTitle("Importante");
                alert.setHeaderText(null);
                alert.setContentText("Selecione o escopo.");
                alert.showAndWait();
            }
        }else{
            alert.setTitle("Importante");
            alert.setHeaderText(null);
            alert.setContentText("Ainda existem campos obrigatórios não preenchidos.");
            alert.showAndWait();
        }

        if(zipMaxpar.isSelected()){
            zipaArquivo();
        }
    }

    public void salvaArquivoComum(){
        fileChooser.setInitialFileName("PAT_"+patComum.getText());
        FileChooser.ExtensionFilter sqlFilter = new FileChooser.ExtensionFilter("SQL","*.sql");
        fileChooser.getExtensionFilters().add(sqlFilter);
        fileChooser.setSelectedExtensionFilter(sqlFilter);
        file = fileChooser.showSaveDialog(null);
    }
    public void salvaArquivoMaxpar(){
        fileChooser.setInitialFileName("PAT_"+patMaxpar.getText());
        FileChooser.ExtensionFilter sqlFilter = new FileChooser.ExtensionFilter("SQL","*.sql");
        fileChooser.getExtensionFilters().add(sqlFilter);
        fileChooser.setSelectedExtensionFilter(sqlFilter);
        file = fileChooser.showSaveDialog(null);
    }

    public void zipaArquivo() throws IOException {

        File zip = new File(file.getPath().replace(".sql", ".zip"));
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip));

        ZipEntry entry = new ZipEntry(file.getName());
        zos.putNextEntry(entry);

        FileInputStream fis = new FileInputStream(file);
        byte[] byteBuffer = new byte[1024];
        int bytesRead = -1;
        while((bytesRead = fis.read(byteBuffer)) != -1){
            zos.write(byteBuffer, 0, bytesRead);
        }
        zos.flush();
        fis.close();
        zos.close();

    }
    public void escritorConsultaComum() throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(file));
        String consultaComum;
        {
            consultaComum = "SET FEEDBACK OFF VERIFY OFF PAGESIZE 0 LINES 1000 TRIMSPOOL ON\n" +
                    "\n" +
                    "PROMPT===============================================================\n" +
                    "PROMPT VOCÊ PRECISARÁ DE PERMISSÃO PARA GRAVAR O LOG EM <C:>\n" +
                    "PROMPT PRESSIONE <ENTER> PARA CONTINUAR.\n" +
                    "PROMPT===============================================================\n" +
                    "\n" +
                    "PAUSE\n" +
                    "\n" +
                    "SPOOL C:\\temp\\LOG_"+patComum.getText()+".LOG\n" +
                    "\n" +
                    "PROMPT \n" +
                    "PROMPT\n" +
                    "PROMPT ===========================================================\n" +
                    "PROMPT =          ::                                             =\n" +
                    "PROMPT =        ::::::                                           =\n" +
                    "PROMPT =      ::::::::::                                         =\n" +
                    "PROMPT =    ::::::::::::::        #        # ##    #  #        # =\n" +
                    "PROMPT =  ::::::::::::::::::      ##      ##  ##  #   ##      ## =\n" +
                    "PROMPT = .. ::::::::::::::  ##    ###    ###   ###    ###    ### =\n" +
                    "PROMPT = .... ::::::::::  ####    ####  # ##    ##    ####  #### =\n" +
                    "PROMPT = ...... ::::::  ######    #  ###  ##   # ##   #  ###  ## =\n" +
                    "PROMPT = ........ ::  ########    #   #   ##  #   ##  #   #   ## =\n" +
                    "PROMPT = .......... ##########    #       ## #     ## #       ## =\n" +
                    "PROMPT = .......... ##########    ############################## =\n" +
                    "PROMPT =   ........ ########        _     _ ___  _       _   _   =\n" +
                    "PROMPT =     ...... ######         |_  | |_  |  |_ |\\/| |_| |_   =\n" +
                    "PROMPT =       .... ####            _| |  _| |  |_ |  | | |  _|  =\n" +
                    "PROMPT =         .. ##                                           =\n" +
                    "PROMPT =                                                         =\n" +
                    "PROMPT ===========================================================\n" +
                    "PROMPT \n" +
                    "PROMPT \n" +
                    "ALTER SESSION SET NLS_DATE_FORMAT='DD/MM/RRRR';\n" +
                    "SELECT  'Usuário OS.......:     '|| SYS_CONTEXT ('USERENV', 'OS_USER')       ||CHR(10)||\n" +
                    "\t\t'Usuário Oracle...:     '|| SYS_CONTEXT ('USERENV', 'SESSION_USER')  ||CHR(10)||        \n" +
                    "\t\t'Schema Corrente..:     '|| SYS_CONTEXT ('USERENV', 'CURRENT_SCHEMA')||CHR(10)||\n" +
                    "\t\t'SID..............:     '|| SYS_CONTEXT ('USERENV', 'DB_NAME')       ||CHR(10)||        \n" +
                    "        'Data/Hora........:     '|| TO_CHAR(SYSDATE, 'DD/MM/RRRR HH24:MI:SS') AS IDENTIFICACAO FROM DUAL;\n" +
                    "\n" +
                    "SET SERVEROUT ON\n" +
                    "\n" +
                    "DECLARE\n" +
                    "   VAR_USO       VARCHAR2 (32);\n" +
                    "   VAR_NUMOBJ    NUMBER;\n" +
                    "   VAR_TEXTO     VARCHAR2 (70);\n" +
                    "   VAR_PROMPT1   VARCHAR2 (50);\n" +
                    "BEGIN\n" +
                    "   SELECT USERNAME\n" +
                    "     INTO VAR_USO\n" +
                    "     FROM USER_USERS;\n" +
                    "\n" +
                    "   SELECT COUNT (1)\n" +
                    "     INTO VAR_NUMOBJ\n" +
                    "     FROM USER_OBJECTS\n" +
                    "    WHERE OBJECT_NAME IN ('LANCCTB_LCT', 'TITCP_TCP', 'CLIENTE_CLI');\n" +
                    "\n" +
                    "   IF VAR_NUMOBJ = 0 OR VAR_USO IN ('SYSTEM', 'SYS')\n" +
                    "   THEN\n" +
                    "      VAR_TEXTO := '    ESTE SCRIPT NÃO PODE SER APLICADO COMO ' || VAR_USO || '!';\n" +
                    "      VAR_PROMPT1 := '===============================================';\n" +
                    "   END IF;\n" +
                    "\n" +
                    "   DBMS_OUTPUT.PUT_LINE (VAR_PROMPT1);\n" +
                    "   DBMS_OUTPUT.PUT_LINE (VAR_TEXTO);\n" +
                    " --  DBMS_OUTPUT.PUT_LINE (VAR_PROMPT1);\n" +
                    "END;\n" +
                    "/\n" +
                    "SET FEEDBACK ON VERIFY ON\n" +
                    "SET SERVEROUT OFF\n" +
                    "\n" +
                    "\n" +
                    "PROMPT               PRESSIONE <ENTER> PARA CONTINUAR\n" +
                    "PROMPT=================================================================\n" +
                    "PAUSE\n" +
                    "\n" +
                    "WHENEVER SQLERROR EXIT\n" +
                    "DECLARE\n" +
                    "   VAR_USO       VARCHAR2 (32);\n" +
                    "   VAR_NUMOBJ    NUMBER;\n" +
                    "   VAR_TEXTO     VARCHAR2 (70);\n" +
                    "   VAR_PROMPT1   VARCHAR2 (50);\n" +
                    "BEGIN\n" +
                    "   SELECT USERNAME\n" +
                    "     INTO VAR_USO\n" +
                    "     FROM USER_USERS;\n" +
                    "\n" +
                    "   SELECT COUNT (1)\n" +
                    "     INTO VAR_NUMOBJ\n" +
                    "     FROM USER_OBJECTS\n" +
                    "    WHERE OBJECT_NAME IN ('LANCCTB_LCT', 'TITCP_TCP', 'CLIENTE_CLI');\n" +
                    "\n" +
                    "   IF VAR_NUMOBJ = 0 OR VAR_USO IN ('SYSTEM', 'SYS')\n" +
                    "   THEN\n" +
                    "      RAISE_APPLICATION_ERROR (-20000,\n" +
                    "                                  'ESTE SCRIPT NÃO PODE SER APLICADO COMO '\n" +
                    "                               || VAR_USO\n" +
                    "                               || '!'\n" +
                    "                              );\n" +
                    "   END IF;\n" +
                    "END;\n" +
                    "/\n" +
                    "WHENEVER SQLERROR CONTINUE\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT =                                < INICIO DO SCRIPT >                                                  =\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SET PAGES 1000 FEEDBACK ON TRIMSPOOL ON LINES 10000 \n" +
                    "SET ECHO ON COLSEP  ' | '\n" +
                    "\n" +
                    "\n" +
                    "\n" +scriptComum.getText()+
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "COMMIT;\n" +
                    "SET ECHO OFF COLSEP  ' '\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT OBJETOS INVÁLIDOS\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SELECT OBJECT_TYPE, OBJECT_NAME\n" +
                    "FROM USER_OBJECTS\n" +
                    "WHERE STATUS <> 'VALID' ;\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT = DESCRICAO DOS ERROS ENCONTRADOS                                                              \n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT \n" +
                    "SET TRIMSPOOL ON PAGES 0 LINES 10000 LONG 1000000\n" +
                    "SELECT \n" +
                    "'TIPO...: '  ||  UE.TYPE || CHR(10) ||\n" +
                    "'NOME...: '  ||  UE.NAME || CHR(10) ||\n" +
                    "'ERRO...: '  ||  UE.TEXT || CHR(10) ||\n" +
                    "'LINHA..: '  || CHR(10)  || SUBSTR(US.TEXT, UE.POSITION) || CHR(10)\n" +
                    "FROM \n" +
                    "USER_ERRORS UE, USER_SOURCE US \n" +
                    "WHERE UE.TYPE = US.TYPE \n" +
                    "AND   UE.NAME = US.NAME\n" +
                    "AND   UE.LINE = US.LINE\n" +
                    "AND   UE.TYPE <> 'VIEW'\n" +
                    "ORDER BY UE.TYPE, UE.NAME;\n" +
                    "\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT VERIFICANDO VERSAO DO MANAGER\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SELECT * FROM VERSAO_VER ORDER BY 1;\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT VERIFICANDO VERSAO DO ORACLE\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SELECT * FROM V$VERSION;\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT VERIFICANDO DATA DE CRIACAO\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SELECT USER, CREATED FROM USER_USERS;\n" +
                    "\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT =                                  < FIM DO SCRIPT >                                                   =\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SPOOL OFF\n" +
                    "CL SCR\n" +
                    "PROMPT FAVOR RETORNAR O LOG QUE FOI GERADO NO CAMINHO \"C:\\temp\\LOG_"+patComum.getText()+".LOG\".\n" +
                    "PROMPT OBRIGADO.\t\t";
        }
        buffWrite.write(consultaComum);
        buffWrite.flush();
        buffWrite.close();
    }
    public void escritorCorrecaoComum() throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(file));
        String correcaoComum;
        {

            correcaoComum = "SET FEEDBACK OFF VERIFY OFF PAGESIZE 0 LINES 1000 TRIMSPOOL ON\n" +
                    "\n" +
                    "PROMPT===============================================================\n" +
                    "PROMPT VOCÊ PRECISARÁ DE PERMISSÃO PARA GRAVAR O LOG EM <C:>\n" +
                    "PROMPT PRESSIONE <ENTER> PARA CONTINUAR.\n" +
                    "PROMPT===============================================================\n" +
                    "\n" +
                    "PAUSE\n" +
                    "\n" +
                    "SPOOL C:\\temp\\LOG_"+patComum.getText()+".LOG\n" +
                    "\n" +
                    "PROMPT \n" +
                    "PROMPT\n" +
                    "PROMPT ===========================================================\n" +
                    "PROMPT =          ::                                             =\n" +
                    "PROMPT =        ::::::                                           =\n" +
                    "PROMPT =      ::::::::::                                         =\n" +
                    "PROMPT =    ::::::::::::::        #        # ##    #  #        # =\n" +
                    "PROMPT =  ::::::::::::::::::      ##      ##  ##  #   ##      ## =\n" +
                    "PROMPT = .. ::::::::::::::  ##    ###    ###   ###    ###    ### =\n" +
                    "PROMPT = .... ::::::::::  ####    ####  # ##    ##    ####  #### =\n" +
                    "PROMPT = ...... ::::::  ######    #  ###  ##   # ##   #  ###  ## =\n" +
                    "PROMPT = ........ ::  ########    #   #   ##  #   ##  #   #   ## =\n" +
                    "PROMPT = .......... ##########    #       ## #     ## #       ## =\n" +
                    "PROMPT = .......... ##########    ############################## =\n" +
                    "PROMPT =   ........ ########        _     _ ___  _       _   _   =\n" +
                    "PROMPT =     ...... ######         |_  | |_  |  |_ |\\/| |_| |_   =\n" +
                    "PROMPT =       .... ####            _| |  _| |  |_ |  | | |  _|  =\n" +
                    "PROMPT =         .. ##                                           =\n" +
                    "PROMPT =                                                         =\n" +
                    "PROMPT ===========================================================\n" +
                    "PROMPT \n" +
                    "PROMPT \n" +
                    "ALTER SESSION SET NLS_DATE_FORMAT='DD/MM/RRRR';\n" +
                    "SELECT  'Usuário OS.......:     '|| SYS_CONTEXT ('USERENV', 'OS_USER')       ||CHR(10)||\n" +
                    "\t\t'Usuário Oracle...:     '|| SYS_CONTEXT ('USERENV', 'SESSION_USER')  ||CHR(10)||        \n" +
                    "\t\t'Schema Corrente..:     '|| SYS_CONTEXT ('USERENV', 'CURRENT_SCHEMA')||CHR(10)||\n" +
                    "\t\t'SID..............:     '|| SYS_CONTEXT ('USERENV', 'DB_NAME')       ||CHR(10)||        \n" +
                    "        'Data/Hora........:     '|| TO_CHAR(SYSDATE, 'DD/MM/RRRR HH24:MI:SS') AS IDENTIFICACAO FROM DUAL;\n" +
                    "\n" +
                    "SET SERVEROUT ON\n" +
                    "\n" +
                    "DECLARE\n" +
                    "   VAR_USO       VARCHAR2 (32);\n" +
                    "   VAR_NUMOBJ    NUMBER;\n" +
                    "   VAR_TEXTO     VARCHAR2 (70);\n" +
                    "   VAR_PROMPT1   VARCHAR2 (50);\n" +
                    "BEGIN\n" +
                    "   SELECT USERNAME\n" +
                    "     INTO VAR_USO\n" +
                    "     FROM USER_USERS;\n" +
                    "\n" +
                    "   SELECT COUNT (1)\n" +
                    "     INTO VAR_NUMOBJ\n" +
                    "     FROM USER_OBJECTS\n" +
                    "    WHERE OBJECT_NAME IN ('LANCCTB_LCT', 'TITCP_TCP', 'CLIENTE_CLI');\n" +
                    "\n" +
                    "   IF VAR_NUMOBJ = 0 OR VAR_USO IN ('SYSTEM', 'SYS')\n" +
                    "   THEN\n" +
                    "      VAR_TEXTO := '    ESTE SCRIPT NÃO PODE SER APLICADO COMO ' || VAR_USO || '!';\n" +
                    "      VAR_PROMPT1 := '===============================================';\n" +
                    "   END IF;\n" +
                    "\n" +
                    "   DBMS_OUTPUT.PUT_LINE (VAR_PROMPT1);\n" +
                    "   DBMS_OUTPUT.PUT_LINE (VAR_TEXTO);\n" +
                    " --  DBMS_OUTPUT.PUT_LINE (VAR_PROMPT1);\n" +
                    "END;\n" +
                    "/\n" +
                    "SET FEEDBACK ON VERIFY ON\n" +
                    "SET SERVEROUT OFF\n" +
                    "\n" +
                    "\n" +
                    "PROMPT               PRESSIONE <ENTER> PARA CONTINUAR\n" +
                    "PROMPT=================================================================\n" +
                    "PAUSE\n" +
                    "\n" +
                    "WHENEVER SQLERROR EXIT\n" +
                    "DECLARE\n" +
                    "   VAR_USO       VARCHAR2 (32);\n" +
                    "   VAR_NUMOBJ    NUMBER;\n" +
                    "   VAR_TEXTO     VARCHAR2 (70);\n" +
                    "   VAR_PROMPT1   VARCHAR2 (50);\n" +
                    "BEGIN\n" +
                    "   SELECT USERNAME\n" +
                    "     INTO VAR_USO\n" +
                    "     FROM USER_USERS;\n" +
                    "\n" +
                    "   SELECT COUNT (1)\n" +
                    "     INTO VAR_NUMOBJ\n" +
                    "     FROM USER_OBJECTS\n" +
                    "    WHERE OBJECT_NAME IN ('LANCCTB_LCT', 'TITCP_TCP', 'CLIENTE_CLI');\n" +
                    "\n" +
                    "   IF VAR_NUMOBJ = 0 OR VAR_USO IN ('SYSTEM', 'SYS')\n" +
                    "   THEN\n" +
                    "      RAISE_APPLICATION_ERROR (-20000,\n" +
                    "                                  'ESTE SCRIPT NÃO PODE SER APLICADO COMO '\n" +
                    "                               || VAR_USO\n" +
                    "                               || '!'\n" +
                    "                              );\n" +
                    "   END IF;\n" +
                    "END;\n" +
                    "/\n" +
                    "WHENEVER SQLERROR CONTINUE\n" +
                    "PROMPT   ======================================================================================================\n" +
                    "PROMPT   Durante a aplicação do script, poderão ocorrer algumas advertências.\n" +
                    "PROMPT   Abaixo,a listagem daquelas que podem ser ignoradas:\n" +
                    "PROMPT   ======================================================================================================\n" +
                    "PROMPT   -> ORA-00001: unique constraint (USER.TABLE) violated\n" +
                    "PROMPT   -> ORA-00955: name is already used by an existing object\n" +
                    "PROMPT   -> ORA-01418: specified index does not exist\n" +
                    "PROMPT   -> ORA-01430: column being added already exists in table\n" +
                    "PROMPT   -> ORA-01442: column to be modified to NOT NULL is already NOT NULL\n" +
                    "PROMPT   -> ORA-01451: column to be modified to NULL cannot be modified to NULL\n" +
                    "PROMPT   -> ORA-02260: table can have only one primary key\n" +
                    "PROMPT   -> ORA-02261: such unique or primary key already exists in the table\n" +
                    "PROMPT   -> ORA-02264: name already used by an existing constraint\n" +
                    "PROMPT   -> ORA-02275: such a referential constraint already exists in the table\n" +
                    "PROMPT   -> ORA-02443: Cannot drop constraint  - nonexistent constraint\n" +
                    "PROMPT\n" +
                    "PROMPT\n" +
                    "PROMPT   ======================================================================================================\n" +
                    "PROMPT   As advertências listadas abaixo, deverão ser desconsideradas\n" +
                    "PROMPT   desde que os objetos que os tenham causado estejam válidos ao\n" +
                    "PROMPT   final do processo.\n" +
                    "PROMPT   ======================================================================================================\n" +
                    "PROMPT   -> ORA-00904: \"TABLE\".\"FIELD\": invalid identifier\n" +
                    "PROMPT   -> ORA-00942: table or view does not exist\n" +
                    "PROMPT   -> ORA-06575: Package or function is in an invalid state\n" +
                    "PROMPT\n" +
                    "PROMPT\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT =                                < INICIO DO SCRIPT >                                                  =\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SET PAGES 1000 FEEDBACK ON TRIMSPOOL ON LINES 1000\n" +
                    "\n" +
                    "PROMPT VALIDANDO OBJETOS, AGUARDE...\n" +
                    "PROMPT ========================================================================================================\n" +
                    "DECLARE \n" +
                    "\tPRAGMA AUTONOMOUS_TRANSACTION;\t\n" +
                    "\tOBJINVANTES INTEGER;\n" +
                    "\tOBJINVDEPOIS INTEGER;\n" +
                    "\tCURSOR C_OBJ_INVALIDOS IS SELECT      'ALTER '\n" +
                    "\t\t\t|| DECODE (OBJECT_TYPE, 'PACKAGE BODY', 'PACKAGE', OBJECT_TYPE)\n" +
                    "\t\t\t|| ' '\n" +
                    "\t\t\t|| OBJECT_NAME\n" +
                    "\t\t\t|| DECODE (OBJECT_TYPE,\n" +
                    "\t\t\t\t\t\t'PACKAGE BODY', ' COMPILE BODY',\n" +
                    "\t\t\t\t\t\t' COMPILE'\n" +
                    "\t\t\t\t\t) AS COMPILACAO\n" +
                    "\t\tFROM USER_OBJECTS\n" +
                    "\tWHERE STATUS = 'INVALID';\t\n" +
                    "BEGIN\n" +
                    "\tFOR COMANADO IN C_OBJ_INVALIDOS LOOP\n" +
                    "\t\tBEGIN\n" +
                    "\t\t\tEXECUTE IMMEDIATE (COMANADO.COMPILACAO);\n" +
                    "\t\t\tEXCEPTION WHEN OTHERS THEN NULL;\n" +
                    "\t\tEND;\t\t\t\n" +
                    "\tEND LOOP;\n" +
                    "END;\n" +
                    "/\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT OBJETOS INVÁLIDOS (ANTES) \n" +
                    "PROMPT ========================================================================================================\n" +
                    "SELECT OBJECT_TYPE, OBJECT_NAME\n" +
                    "FROM USER_OBJECTS\n" +
                    "WHERE STATUS <> 'VALID' ;\n" +
                    "PROMPT ========================================================================================================\n" +
                    "\n" +
                    "\n" +
                    "\n" +scriptComum.getText()+
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "COMMIT;\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT CONCEDENDO PERMISSOES AOS OBJETOS CRIADOS, AGUARDE...\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SET SERVEROUT ON\n" +
                    "DECLARE \n" +
                    "\tPRAGMA AUTONOMOUS_TRANSACTION;\t\n" +
                    "\tOBJINVANTES INTEGER;\n" +
                    "\tOBJINVDEPOIS INTEGER;\n" +
                    "\tCURSOR C_OBJ_GRANTS IS SELECT  'GRANT  '||\n" +
                    "                    DECODE(OBJECT_TYPE,\n" +
                    "            \t\t\t   'TABLE'   ,'SELECT,INSERT,UPDATE,DELETE',\n" +
                    "            \t\t\t   'VIEW'    ,'SELECT',\n" +
                    "            \t\t\t   'SEQUENCE','SELECT',\n" +
                    "            \t\t\t   'EXECUTE'\n" +
                    "            \t\t\t  )||\n" +
                    "            ' ON '||OBJECT_NAME||' TO MXMSYS, MXMDBA' AS PERMISSAO\n" +
                    "            FROM USER_OBJECTS\n" +
                    "            WHERE OBJECT_TYPE IN ('PROCEDURE','FUNCTION','VIEW','TABLE','SEQUENCE','PACKAGE','PACKAGE BODY')\n" +
                    "            AND CREATED > TO_DATE(SYSDATE,'DD/MM/RRRR') - 3\n" +
                    "            ORDER BY 1;\t\n" +
                    "BEGIN\n" +
                    "\tFOR COMANADO IN C_OBJ_GRANTS LOOP\n" +
                    "\t\tBEGIN\t\t    \n" +
                    "\t\t\tEXECUTE IMMEDIATE (COMANADO.PERMISSAO);\n" +
                    "\t\t\tDBMS_OUTPUT.PUT_LINE('--> O COMANDO \"'||COMANADO.PERMISSAO||'\" FOI BEM-SUCEDIDO.');\n" +
                    "\t\t\tEXCEPTION WHEN OTHERS THEN NULL;\n" +
                    "\t\tEND;\t\t\t\n" +
                    "\tEND LOOP;\n" +
                    "END;\n" +
                    "/\n" +
                    "\n" +
                    "\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT CRIANDO SINONIMOS AOS OBJETOS CRIADOS(CASO AMBIENTE=2), AGUARDE...\n" +
                    "PROMPT ========================================================================================================\n" +
                    "DECLARE \n" +
                    "\tPRAGMA AUTONOMOUS_TRANSACTION;\t\n" +
                    "\tCONTADOR   INTEGER;\n" +
                    "\tCURSOR C_OBJ_SINONIMOS IS SELECT  'CREATE PUBLIC SYNONYM '||OBJECT_NAME||' FOR '||OBJECT_NAME AS SINONIMO\n" +
                    "                           FROM USER_OBJECTS\n" +
                    "                           WHERE OBJECT_TYPE IN ('PROCEDURE','FUNCTION','VIEW','TABLE','SEQUENCE','PACKAGE','PACKAGE BODY')\n" +
                    "                           AND\n" +
                    "                                 NOT EXISTS (SELECT SYNONYM_NAME \n" +
                    "                                             FROM ALL_SYNONYMS\n" +
                    "                                             WHERE OWNER='PUBLIC' AND\n" +
                    "                                                   TABLE_OWNER=user AND\n" +
                    "                                                    SYNONYM_NAME = OBJECT_NAME)\n" +
                    "                           ORDER BY 1;\t\n" +
                    "BEGIN\n" +
                    "\tSELECT COUNT(1) INTO contador  FROM all_synonyms\n" +
                    "\tWHERE owner = 'PUBLIC' AND table_owner = USER  AND table_name in ('CLIENTE_CLI','PLANOCTA_PLC','LANCCTB_LCT','TITCP_TCP');\n" +
                    "\tIF contador <> 4 THEN\n" +
                    "\t\tDBMS_OUTPUT.put_line ('A base não possui sinônimos.');\n" +
                    "\tELSE\n" +
                    "\t\tDBMS_OUTPUT.put_line ('A base possui sinônimos.');\n" +
                    "\t    FOR COMANADO IN C_OBJ_SINONIMOS LOOP\n" +
                    "\t    \tBEGIN\n" +
                    "\t    \t\tEXECUTE IMMEDIATE (COMANADO.SINONIMO);\n" +
                    "\t    \t\tEXCEPTION WHEN OTHERS THEN NULL;\n" +
                    "\t    \tEND;\t\t\t\n" +
                    "\t    END LOOP;\n" +
                    "\tEND IF;\t\n" +
                    "END;\n" +
                    "/\n" +
                    "\n" +
                    "\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT VALIDANDO OBJETOS, AGUARDE...\n" +
                    "PROMPT ========================================================================================================\n" +
                    "DECLARE \n" +
                    "\tPRAGMA AUTONOMOUS_TRANSACTION;\t\n" +
                    "\tOBJINVANTES INTEGER;\n" +
                    "\tOBJINVDEPOIS INTEGER;\n" +
                    "\tCURSOR C_OBJ_INVALIDOS IS SELECT      'ALTER '\n" +
                    "\t\t\t|| DECODE (OBJECT_TYPE, 'PACKAGE BODY', 'PACKAGE', OBJECT_TYPE)\n" +
                    "\t\t\t|| ' '\n" +
                    "\t\t\t|| OBJECT_NAME\n" +
                    "\t\t\t|| DECODE (OBJECT_TYPE,\n" +
                    "\t\t\t\t\t\t'PACKAGE BODY', ' COMPILE BODY',\n" +
                    "\t\t\t\t\t\t' COMPILE'\n" +
                    "\t\t\t\t\t) AS COMPILACAO\n" +
                    "\t\tFROM USER_OBJECTS\n" +
                    "\tWHERE STATUS = 'INVALID';\t\n" +
                    "BEGIN\n" +
                    "\tFOR COMANADO IN C_OBJ_INVALIDOS LOOP\n" +
                    "\t\tBEGIN\n" +
                    "\t\t\tEXECUTE IMMEDIATE (COMANADO.COMPILACAO);\n" +
                    "\t\t\tEXCEPTION WHEN OTHERS THEN NULL;\n" +
                    "\t\tEND;\t\t\t\n" +
                    "\tEND LOOP;\n" +
                    "END;\n" +
                    "/\n" +
                    "\n" +
                    "DECLARE \n" +
                    "\tPRAGMA AUTONOMOUS_TRANSACTION;\t\n" +
                    "\tOBJINVANTES INTEGER;\n" +
                    "\tOBJINVDEPOIS INTEGER;\n" +
                    "\tCURSOR C_OBJ_INVALIDOS IS SELECT      'ALTER '\n" +
                    "         || DECODE (OBJECT_TYPE, 'PACKAGE BODY', 'PACKAGE', OBJECT_TYPE)\n" +
                    "         || ' '\n" +
                    "         || OBJECT_NAME\n" +
                    "         || DECODE (OBJECT_TYPE,\n" +
                    "                    'PACKAGE BODY', ' COMPILE BODY;',\n" +
                    "                    ' COMPILE;'\n" +
                    "                   ) AS COMPILACAO\n" +
                    "    FROM USER_OBJECTS\n" +
                    "   WHERE STATUS = 'INVALID'\n" +
                    "ORDER BY OBJECT_NAME;\t\n" +
                    "BEGIN\n" +
                    "\tFOR COMANADO IN C_OBJ_INVALIDOS LOOP\n" +
                    "\t\tBEGIN\n" +
                    "\t\t\tEXECUTE IMMEDIATE (COMANADO.COMPILACAO);\n" +
                    "\t\t\tEXCEPTION WHEN OTHERS THEN NULL;\n" +
                    "\t\tEND;\t\t\t\n" +
                    "\tEND LOOP;\n" +
                    "END;\n" +
                    "/\n" +
                    "\n" +
                    "EXEC MXMVALIDA_80;\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT OBJETOS INVÁLIDOS (DEPOIS) \n" +
                    "PROMPT ========================================================================================================\n" +
                    "SELECT OBJECT_TYPE, OBJECT_NAME\n" +
                    "FROM USER_OBJECTS\n" +
                    "WHERE STATUS <> 'VALID' ;\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT = DESCRICAO DOS ERROS ENCONTRADOS                                                              \n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT \n" +
                    "SET TRIMSPOOL ON PAGES 0 LINES 10000 LONG 1000000\n" +
                    "SELECT \n" +
                    "'TIPO...: '  ||  UE.TYPE || CHR(10) ||\n" +
                    "'NOME...: '  ||  UE.NAME || CHR(10) ||\n" +
                    "'ERRO...: '  ||  UE.TEXT || CHR(10) ||\n" +
                    "'LINHA..: '  || CHR(10)  || SUBSTR(US.TEXT, UE.POSITION) || CHR(10)\n" +
                    "FROM \n" +
                    "USER_ERRORS UE, USER_SOURCE US \n" +
                    "WHERE UE.TYPE = US.TYPE \n" +
                    "AND   UE.NAME = US.NAME\n" +
                    "AND   UE.LINE = US.LINE\n" +
                    "AND   UE.TYPE <> 'VIEW'\n" +
                    "ORDER BY UE.TYPE, UE.NAME;\n" +
                    "\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT VERIFICANDO VERSAO DO MANAGER\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SELECT * FROM VERSAO_VER ORDER BY 1;\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT VERIFICANDO VERSAO DO ORACLE\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SELECT * FROM V$VERSION;\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT VERIFICANDO DATA DE CRIACAO\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SELECT USER, CREATED FROM USER_USERS;\n" +
                    "\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT =                                  < FIM DO SCRIPT >                                                   =\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SPOOL OFF\n" +
                    "CL SCR\n" +
                    "PROMPT FAVOR REALIZAR O TESTE NOVAMENTE\n" +
                    "PROMPT CASO O ERRO PERSISTA, FAVOR RETORNAR O LOG QUE FOI GERADO NO CAMINHO \"C:\\temp\\LOG_"+patComum.getText()+".LOG\".\n" +
                    "PROMPT OBRIGADO.\t\t";
        }
        buffWrite.write(correcaoComum);
        buffWrite.flush();
        buffWrite.close();
    }
    public void escritorConsultaMaxpar() throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(file));

        String consultaMaxpar;
        {
            consultaMaxpar = "SET FEEDBACK OFF VERIFY OFF PAGESIZE 0 LINES 1000 TRIMSPOOL ON\n" +
                    "\n" +
                    "PROMPT===============================================================\n" +
                    "PROMPT VOCÊ PRECISARÁ DE PERMISSÃO PARA GRAVAR O LOG EM <C:>\n" +
                    "PROMPT PRESSIONE <ENTER> PARA CONTINUAR.\n" +
                    "PROMPT===============================================================\n" +
                    "\n" +
                    "PAUSE\n" +
                    "\n" +
                    "SPOOL C:\\temp\\LOG_"+patMaxpar.getText()+".LOG\n" +
                    "\n" +
                    "PROMPT \n" +
                    "PROMPT\n" +
                    "PROMPT ===========================================================\n" +
                    "PROMPT =          ::                                             =\n" +
                    "PROMPT =        ::::::                                           =\n" +
                    "PROMPT =      ::::::::::                                         =\n" +
                    "PROMPT =    ::::::::::::::        #        # ##    #  #        # =\n" +
                    "PROMPT =  ::::::::::::::::::      ##      ##  ##  #   ##      ## =\n" +
                    "PROMPT = .. ::::::::::::::  ##    ###    ###   ###    ###    ### =\n" +
                    "PROMPT = .... ::::::::::  ####    ####  # ##    ##    ####  #### =\n" +
                    "PROMPT = ...... ::::::  ######    #  ###  ##   # ##   #  ###  ## =\n" +
                    "PROMPT = ........ ::  ########    #   #   ##  #   ##  #   #   ## =\n" +
                    "PROMPT = .......... ##########    #       ## #     ## #       ## =\n" +
                    "PROMPT = .......... ##########    ############################## =\n" +
                    "PROMPT =   ........ ########        _     _ ___  _       _   _   =\n" +
                    "PROMPT =     ...... ######         |_  | |_  |  |_ |\\/| |_| |_   =\n" +
                    "PROMPT =       .... ####            _| |  _| |  |_ |  | | |  _|  =\n" +
                    "PROMPT =         .. ##                                           =\n" +
                    "PROMPT =                                                         =\n" +
                    "PROMPT ===========================================================\n" +
                    "PROMPT \n" +
                    "PROMPT \n" +
                    "ALTER SESSION SET NLS_DATE_FORMAT='DD/MM/RRRR';\n" +
                    "SELECT  'Usuário OS.......:     '|| SYS_CONTEXT ('USERENV', 'OS_USER')       ||CHR(10)||\n" +
                    "\t\t'Usuário Oracle...:     '|| SYS_CONTEXT ('USERENV', 'SESSION_USER')  ||CHR(10)||        \n" +
                    "\t\t'Schema Corrente..:     '|| SYS_CONTEXT ('USERENV', 'CURRENT_SCHEMA')||CHR(10)||\n" +
                    "\t\t'SID..............:     '|| SYS_CONTEXT ('USERENV', 'DB_NAME')       ||CHR(10)||        \n" +
                    "        'Data/Hora........:     '|| TO_CHAR(SYSDATE, 'DD/MM/RRRR HH24:MI:SS') AS IDENTIFICACAO FROM DUAL;\n" +
                    "\n" +
                    "SET SERVEROUT ON\n" +
                    "\n" +
                    "DECLARE\n" +
                    "   VAR_USO       VARCHAR2 (32);\n" +
                    "   VAR_NUMOBJ    NUMBER;\n" +
                    "   VAR_TEXTO     VARCHAR2 (70);\n" +
                    "   VAR_PROMPT1   VARCHAR2 (50);\n" +
                    "BEGIN\n" +
                    "   SELECT USERNAME\n" +
                    "     INTO VAR_USO\n" +
                    "     FROM USER_USERS;\n" +
                    "\n" +
                    "   SELECT COUNT (1)\n" +
                    "     INTO VAR_NUMOBJ\n" +
                    "     FROM USER_OBJECTS\n" +
                    "    WHERE OBJECT_NAME IN ('LANCCTB_LCT', 'TITCP_TCP', 'CLIENTE_CLI');\n" +
                    "\n" +
                    "   IF VAR_NUMOBJ = 0 OR VAR_USO IN ('SYSTEM', 'SYS')\n" +
                    "   THEN\n" +
                    "      VAR_TEXTO := '    ESTE SCRIPT NÃO PODE SER APLICADO COMO ' || VAR_USO || '!';\n" +
                    "      VAR_PROMPT1 := '===============================================';\n" +
                    "   END IF;\n" +
                    "\n" +
                    "   DBMS_OUTPUT.PUT_LINE (VAR_PROMPT1);\n" +
                    "   DBMS_OUTPUT.PUT_LINE (VAR_TEXTO);\n" +
                    " --  DBMS_OUTPUT.PUT_LINE (VAR_PROMPT1);\n" +
                    "END;\n" +
                    "/\n" +
                    "SET FEEDBACK ON VERIFY ON\n" +
                    "SET SERVEROUT OFF\n" +
                    "\n" +
                    "\n" +
                    "PROMPT               PRESSIONE <ENTER> PARA CONTINUAR\n" +
                    "PROMPT=================================================================\n" +
                    "PAUSE\n" +
                    "\n" +
                    "WHENEVER SQLERROR EXIT\n" +
                    "DECLARE\n" +
                    "   VAR_USO       VARCHAR2 (32);\n" +
                    "   VAR_NUMOBJ    NUMBER;\n" +
                    "   VAR_TEXTO     VARCHAR2 (70);\n" +
                    "   VAR_PROMPT1   VARCHAR2 (50);\n" +
                    "BEGIN\n" +
                    "   SELECT USERNAME\n" +
                    "     INTO VAR_USO\n" +
                    "     FROM USER_USERS;\n" +
                    "\n" +
                    "   SELECT COUNT (1)\n" +
                    "     INTO VAR_NUMOBJ\n" +
                    "     FROM USER_OBJECTS\n" +
                    "    WHERE OBJECT_NAME IN ('LANCCTB_LCT', 'TITCP_TCP', 'CLIENTE_CLI');\n" +
                    "\n" +
                    "   IF VAR_NUMOBJ = 0 OR VAR_USO IN ('SYSTEM', 'SYS')\n" +
                    "   THEN\n" +
                    "      RAISE_APPLICATION_ERROR (-20000,\n" +
                    "                                  'ESTE SCRIPT NÃO PODE SER APLICADO COMO '\n" +
                    "                               || VAR_USO\n" +
                    "                               || '!'\n" +
                    "                              );\n" +
                    "   END IF;\n" +
                    "END;\n" +
                    "/\n" +
                    "WHENEVER SQLERROR CONTINUE\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT =                                < INICIO DO SCRIPT >                                                  =\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SET PAGES 1000 FEEDBACK ON TRIMSPOOL ON LINES 10000 \n" +
                    "SET ECHO ON COLSEP  ' | '\n" +
                    "\n" +
                    "\n" +
                    "\n" +scriptMaxpar.getText()+
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "COMMIT;\n" +
                    "SET ECHO OFF COLSEP  ' '\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT OBJETOS INVÁLIDOS\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SELECT OBJECT_TYPE, OBJECT_NAME\n" +
                    "FROM USER_OBJECTS\n" +
                    "WHERE STATUS <> 'VALID' ;\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT = DESCRICAO DOS ERROS ENCONTRADOS                                                              \n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT \n" +
                    "SET TRIMSPOOL ON PAGES 0 LINES 10000 LONG 1000000\n" +
                    "SELECT \n" +
                    "'TIPO...: '  ||  UE.TYPE || CHR(10) ||\n" +
                    "'NOME...: '  ||  UE.NAME || CHR(10) ||\n" +
                    "'ERRO...: '  ||  UE.TEXT || CHR(10) ||\n" +
                    "'LINHA..: '  || CHR(10)  || SUBSTR(US.TEXT, UE.POSITION) || CHR(10)\n" +
                    "FROM \n" +
                    "USER_ERRORS UE, USER_SOURCE US \n" +
                    "WHERE UE.TYPE = US.TYPE \n" +
                    "AND   UE.NAME = US.NAME\n" +
                    "AND   UE.LINE = US.LINE\n" +
                    "AND   UE.TYPE <> 'VIEW'\n" +
                    "ORDER BY UE.TYPE, UE.NAME;\n" +
                    "\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT VERIFICANDO VERSAO DO MANAGER\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SELECT * FROM VERSAO_VER ORDER BY 1;\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT VERIFICANDO VERSAO DO ORACLE\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SELECT * FROM V$VERSION;\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT VERIFICANDO DATA DE CRIACAO\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SELECT USER, CREATED FROM USER_USERS;\n" +
                    "\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT =                                  < FIM DO SCRIPT >                                                   =\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SPOOL OFF\n" +
                    "CL SCR\n" +
                    "PROMPT FAVOR RETORNAR O LOG QUE FOI GERADO NO CAMINHO \"C:\\temp\\LOG_"+patMaxpar.getText()+".LOG\".\n" +
                    "PROMPT OBRIGADO.\t\t";
        }
        buffWrite.write(consultaMaxpar);
        buffWrite.flush();
        buffWrite.close();
    }
    public void escritorDadosMaxpar() throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(file));

        String dadosMaxpar;
        {
            dadosMaxpar = "SET FEEDBACK OFF VERIFY OFF PAGESIZE 0 LINES 1000 TRIMSPOOL ON\n" +
                    "\n" +
                    "PROMPT===============================================================\n" +
                    "PROMPT VOCÊ PRECISARÁ DE PERMISSÃO PARA GRAVAR O LOG EM <C:>\n" +
                    "PROMPT PRESSIONE <ENTER> PARA CONTINUAR.\n" +
                    "PROMPT===============================================================\n" +
                    "\n" +
                    "PAUSE\n" +
                    "\n" +
                    "SPOOL C:\\temp\\LOG_"+patMaxpar.getText()+".LOG\n" +
                    "\n" +
                    "PROMPT \n" +
                    "PROMPT\n" +
                    "PROMPT ===========================================================\n" +
                    "PROMPT =          ::                                             =\n" +
                    "PROMPT =        ::::::                                           =\n" +
                    "PROMPT =      ::::::::::                                         =\n" +
                    "PROMPT =    ::::::::::::::        #        # ##    #  #        # =\n" +
                    "PROMPT =  ::::::::::::::::::      ##      ##  ##  #   ##      ## =\n" +
                    "PROMPT = .. ::::::::::::::  ##    ###    ###   ###    ###    ### =\n" +
                    "PROMPT = .... ::::::::::  ####    ####  # ##    ##    ####  #### =\n" +
                    "PROMPT = ...... ::::::  ######    #  ###  ##   # ##   #  ###  ## =\n" +
                    "PROMPT = ........ ::  ########    #   #   ##  #   ##  #   #   ## =\n" +
                    "PROMPT = .......... ##########    #       ## #     ## #       ## =\n" +
                    "PROMPT = .......... ##########    ############################## =\n" +
                    "PROMPT =   ........ ########        _     _ ___  _       _   _   =\n" +
                    "PROMPT =     ...... ######         |_  | |_  |  |_ |\\/| |_| |_   =\n" +
                    "PROMPT =       .... ####            _| |  _| |  |_ |  | | |  _|  =\n" +
                    "PROMPT =         .. ##                                           =\n" +
                    "PROMPT =                                                         =\n" +
                    "PROMPT ===========================================================\n" +
                    "PROMPT \n" +
                    "PROMPT \n" +
                    "ALTER SESSION SET NLS_DATE_FORMAT='DD/MM/RRRR';\n" +
                    "SELECT  'Usuário OS.......:     '|| SYS_CONTEXT ('USERENV', 'OS_USER')       ||CHR(10)||\n" +
                    "\t\t'Usuário Oracle...:     '|| SYS_CONTEXT ('USERENV', 'SESSION_USER')  ||CHR(10)||        \n" +
                    "\t\t'Schema Corrente..:     '|| SYS_CONTEXT ('USERENV', 'CURRENT_SCHEMA')||CHR(10)||\n" +
                    "\t\t'SID..............:     '|| SYS_CONTEXT ('USERENV', 'DB_NAME')       ||CHR(10)||        \n" +
                    "        'Data/Hora........:     '|| TO_CHAR(SYSDATE, 'DD/MM/RRRR HH24:MI:SS') AS IDENTIFICACAO FROM DUAL;\n" +
                    "\n" +
                    "SET SERVEROUT ON\n" +
                    "\n" +
                    "DECLARE\n" +
                    "   VAR_USO       VARCHAR2 (32);\n" +
                    "   VAR_NUMOBJ    NUMBER;\n" +
                    "   VAR_TEXTO     VARCHAR2 (70);\n" +
                    "   VAR_PROMPT1   VARCHAR2 (50);\n" +
                    "BEGIN\n" +
                    "   SELECT USERNAME\n" +
                    "     INTO VAR_USO\n" +
                    "     FROM USER_USERS;\n" +
                    "\n" +
                    "   SELECT COUNT (1)\n" +
                    "     INTO VAR_NUMOBJ\n" +
                    "     FROM USER_OBJECTS\n" +
                    "    WHERE OBJECT_NAME IN ('LANCCTB_LCT', 'TITCP_TCP', 'CLIENTE_CLI');\n" +
                    "\n" +
                    "   IF VAR_NUMOBJ = 0 OR VAR_USO IN ('SYSTEM', 'SYS')\n" +
                    "   THEN\n" +
                    "      VAR_TEXTO := '    ESTE SCRIPT NÃO PODE SER APLICADO COMO ' || VAR_USO || '!';\n" +
                    "      VAR_PROMPT1 := '===============================================';\n" +
                    "   END IF;\n" +
                    "\n" +
                    "   DBMS_OUTPUT.PUT_LINE (VAR_PROMPT1);\n" +
                    "   DBMS_OUTPUT.PUT_LINE (VAR_TEXTO);\n" +
                    " --  DBMS_OUTPUT.PUT_LINE (VAR_PROMPT1);\n" +
                    "END;\n" +
                    "/\n" +
                    "SET FEEDBACK ON VERIFY ON\n" +
                    "SET SERVEROUT OFF\n" +
                    "\n" +
                    "\n" +
                    "PROMPT               PRESSIONE <ENTER> PARA CONTINUAR\n" +
                    "PROMPT=================================================================\n" +
                    "PAUSE\n" +
                    "\n" +
                    "WHENEVER SQLERROR EXIT\n" +
                    "DECLARE\n" +
                    "   VAR_USO       VARCHAR2 (32);\n" +
                    "   VAR_NUMOBJ    NUMBER;\n" +
                    "   VAR_TEXTO     VARCHAR2 (70);\n" +
                    "   VAR_PROMPT1   VARCHAR2 (50);\n" +
                    "BEGIN\n" +
                    "   SELECT USERNAME\n" +
                    "     INTO VAR_USO\n" +
                    "     FROM USER_USERS;\n" +
                    "\n" +
                    "   SELECT COUNT (1)\n" +
                    "     INTO VAR_NUMOBJ\n" +
                    "     FROM USER_OBJECTS\n" +
                    "    WHERE OBJECT_NAME IN ('LANCCTB_LCT', 'TITCP_TCP', 'CLIENTE_CLI');\n" +
                    "\n" +
                    "   IF VAR_NUMOBJ = 0 OR VAR_USO IN ('SYSTEM', 'SYS')\n" +
                    "   THEN\n" +
                    "      RAISE_APPLICATION_ERROR (-20000,\n" +
                    "                                  'ESTE SCRIPT NÃO PODE SER APLICADO COMO '\n" +
                    "                               || VAR_USO\n" +
                    "                               || '!'\n" +
                    "                              );\n" +
                    "   END IF;\n" +
                    "END;\n" +
                    "/\n" +
                    "WHENEVER SQLERROR CONTINUE\n" +
                    "PROMPT\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT =                                < INICIO DO SCRIPT >                                                  =\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SET PAGES 1000 FEEDBACK ON TRIMSPOOL ON LINES 10000 \n" +
                    "\n" +
                    "\n" +
                    "\n" +scriptMaxpar.getText()+
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "COMMIT;\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT VERIFICANDO VERSAO DO MANAGER\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SELECT * FROM VERSAO_VER ORDER BY 1;\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT VERIFICANDO VERSAO DO ORACLE\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SELECT * FROM V$VERSION;\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT VERIFICANDO DATA DE CRIACAO\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SELECT USER, CREATED FROM USER_USERS;\n" +
                    "\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT =                                  < FIM DO SCRIPT >                                                   =\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SPOOL OFF\n" +
                    "CL SCR\n" +
                    "PROMPT LOG GERADO EM \"C:\\temp\\LOG_"+patMaxpar.getText()+".LOG\".\n" +
                    "PROMPT OBRIGADO.\t\t\t";
        }
        buffWrite.write(dadosMaxpar);
        buffWrite.flush();
        buffWrite.close();
    }
    public void escritorEstruturaMaxpar() throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(file));

        String estruturaMaxpar;
        {
            estruturaMaxpar = "SET FEEDBACK OFF VERIFY OFF PAGESIZE 0 LINES 1000 TRIMSPOOL ON\n" +
                    "\n" +
                    "PROMPT===============================================================\n" +
                    "PROMPT VOCÊ PRECISARÁ DE PERMISSÃO PARA GRAVAR O LOG EM <C:>\n" +
                    "PROMPT PRESSIONE <ENTER> PARA CONTINUAR.\n" +
                    "PROMPT===============================================================\n" +
                    "\n" +
                    "PAUSE\n" +
                    "\n" +
                    "SPOOL C:\\temp\\LOG_"+patMaxpar.getText()+".LOG\n" +
                    "\n" +
                    "PROMPT \n" +
                    "PROMPT\n" +
                    "PROMPT ===========================================================\n" +
                    "PROMPT =          ::                                             =\n" +
                    "PROMPT =        ::::::                                           =\n" +
                    "PROMPT =      ::::::::::                                         =\n" +
                    "PROMPT =    ::::::::::::::        #        # ##    #  #        # =\n" +
                    "PROMPT =  ::::::::::::::::::      ##      ##  ##  #   ##      ## =\n" +
                    "PROMPT = .. ::::::::::::::  ##    ###    ###   ###    ###    ### =\n" +
                    "PROMPT = .... ::::::::::  ####    ####  # ##    ##    ####  #### =\n" +
                    "PROMPT = ...... ::::::  ######    #  ###  ##   # ##   #  ###  ## =\n" +
                    "PROMPT = ........ ::  ########    #   #   ##  #   ##  #   #   ## =\n" +
                    "PROMPT = .......... ##########    #       ## #     ## #       ## =\n" +
                    "PROMPT = .......... ##########    ############################## =\n" +
                    "PROMPT =   ........ ########        _     _ ___  _       _   _   =\n" +
                    "PROMPT =     ...... ######         |_  | |_  |  |_ |\\/| |_| |_   =\n" +
                    "PROMPT =       .... ####            _| |  _| |  |_ |  | | |  _|  =\n" +
                    "PROMPT =         .. ##                                           =\n" +
                    "PROMPT =                                                         =\n" +
                    "PROMPT ===========================================================\n" +
                    "PROMPT \n" +
                    "PROMPT \n" +
                    "ALTER SESSION SET NLS_DATE_FORMAT='DD/MM/RRRR';\n" +
                    "SELECT  'Usuário OS.......:     '|| SYS_CONTEXT ('USERENV', 'OS_USER')       ||CHR(10)||\n" +
                    "\t\t'Usuário Oracle...:     '|| SYS_CONTEXT ('USERENV', 'SESSION_USER')  ||CHR(10)||        \n" +
                    "\t\t'Schema Corrente..:     '|| SYS_CONTEXT ('USERENV', 'CURRENT_SCHEMA')||CHR(10)||\n" +
                    "\t\t'SID..............:     '|| SYS_CONTEXT ('USERENV', 'DB_NAME')       ||CHR(10)||        \n" +
                    "        'Data/Hora........:     '|| TO_CHAR(SYSDATE, 'DD/MM/RRRR HH24:MI:SS') AS IDENTIFICACAO FROM DUAL;\n" +
                    "\n" +
                    "SET SERVEROUT ON\n" +
                    "\n" +
                    "DECLARE\n" +
                    "   VAR_USO       VARCHAR2 (32);\n" +
                    "   VAR_NUMOBJ    NUMBER;\n" +
                    "   VAR_TEXTO     VARCHAR2 (70);\n" +
                    "   VAR_PROMPT1   VARCHAR2 (50);\n" +
                    "BEGIN\n" +
                    "   SELECT USERNAME\n" +
                    "     INTO VAR_USO\n" +
                    "     FROM USER_USERS;\n" +
                    "\n" +
                    "   SELECT COUNT (1)\n" +
                    "     INTO VAR_NUMOBJ\n" +
                    "     FROM USER_OBJECTS\n" +
                    "    WHERE OBJECT_NAME IN ('LANCCTB_LCT', 'TITCP_TCP', 'CLIENTE_CLI');\n" +
                    "\n" +
                    "   IF VAR_NUMOBJ = 0 OR VAR_USO IN ('SYSTEM', 'SYS')\n" +
                    "   THEN\n" +
                    "      VAR_TEXTO := '    ESTE SCRIPT NÃO PODE SER APLICADO COMO ' || VAR_USO || '!';\n" +
                    "      VAR_PROMPT1 := '===============================================';\n" +
                    "   END IF;\n" +
                    "\n" +
                    "   DBMS_OUTPUT.PUT_LINE (VAR_PROMPT1);\n" +
                    "   DBMS_OUTPUT.PUT_LINE (VAR_TEXTO);\n" +
                    " --  DBMS_OUTPUT.PUT_LINE (VAR_PROMPT1);\n" +
                    "END;\n" +
                    "/\n" +
                    "SET FEEDBACK ON VERIFY ON\n" +
                    "SET SERVEROUT OFF\n" +
                    "\n" +
                    "\n" +
                    "PROMPT               PRESSIONE <ENTER> PARA CONTINUAR\n" +
                    "PROMPT=================================================================\n" +
                    "PAUSE\n" +
                    "\n" +
                    "WHENEVER SQLERROR EXIT\n" +
                    "DECLARE\n" +
                    "   VAR_USO       VARCHAR2 (32);\n" +
                    "   VAR_NUMOBJ    NUMBER;\n" +
                    "   VAR_TEXTO     VARCHAR2 (70);\n" +
                    "   VAR_PROMPT1   VARCHAR2 (50);\n" +
                    "BEGIN\n" +
                    "   SELECT USERNAME\n" +
                    "     INTO VAR_USO\n" +
                    "     FROM USER_USERS;\n" +
                    "\n" +
                    "   SELECT COUNT (1)\n" +
                    "     INTO VAR_NUMOBJ\n" +
                    "     FROM USER_OBJECTS\n" +
                    "    WHERE OBJECT_NAME IN ('LANCCTB_LCT', 'TITCP_TCP', 'CLIENTE_CLI');\n" +
                    "\n" +
                    "   IF VAR_NUMOBJ = 0 OR VAR_USO IN ('SYSTEM', 'SYS')\n" +
                    "   THEN\n" +
                    "      RAISE_APPLICATION_ERROR (-20000,\n" +
                    "                                  'ESTE SCRIPT NÃO PODE SER APLICADO COMO '\n" +
                    "                               || VAR_USO\n" +
                    "                               || '!'\n" +
                    "                              );\n" +
                    "   END IF;\n" +
                    "END;\n" +
                    "/\n" +
                    "WHENEVER SQLERROR CONTINUE\n" +
                    "PROMPT   ======================================================================================================\n" +
                    "PROMPT   Durante a aplicação do script, poderão ocorrer algumas advertências.\n" +
                    "PROMPT   Abaixo,a listagem daquelas que podem ser ignoradas:\n" +
                    "PROMPT   ======================================================================================================\n" +
                    "PROMPT   -> ORA-00001: unique constraint (USER.TABLE) violated\n" +
                    "PROMPT   -> ORA-00955: name is already used by an existing object\n" +
                    "PROMPT   -> ORA-01418: specified index does not exist\n" +
                    "PROMPT   -> ORA-01430: column being added already exists in table\n" +
                    "PROMPT   -> ORA-01442: column to be modified to NOT NULL is already NOT NULL\n" +
                    "PROMPT   -> ORA-01451: column to be modified to NULL cannot be modified to NULL\n" +
                    "PROMPT   -> ORA-02260: table can have only one primary key\n" +
                    "PROMPT   -> ORA-02261: such unique or primary key already exists in the table\n" +
                    "PROMPT   -> ORA-02264: name already used by an existing constraint\n" +
                    "PROMPT   -> ORA-02275: such a referential constraint already exists in the table\n" +
                    "PROMPT   -> ORA-02443: Cannot drop constraint  - nonexistent constraint\n" +
                    "PROMPT\n" +
                    "PROMPT\n" +
                    "PROMPT   ======================================================================================================\n" +
                    "PROMPT   As advertências listadas abaixo, deverão ser desconsideradas\n" +
                    "PROMPT   desde que os objetos que os tenham causado estejam válidos ao\n" +
                    "PROMPT   final do processo.\n" +
                    "PROMPT   ======================================================================================================\n" +
                    "PROMPT   -> ORA-00904: \"TABLE\".\"FIELD\": invalid identifier\n" +
                    "PROMPT   -> ORA-00942: table or view does not exist\n" +
                    "PROMPT   -> ORA-06575: Package or function is in an invalid state\n" +
                    "PROMPT\n" +
                    "PROMPT\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT =                                < INICIO DO SCRIPT >                                                  =\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SET PAGES 1000 FEEDBACK ON TRIMSPOOL ON LINES 1000\n" +
                    "\n" +
                    "PROMPT VALIDANDO OBJETOS, AGUARDE...\n" +
                    "PROMPT ========================================================================================================\n" +
                    "DECLARE \n" +
                    "\tPRAGMA AUTONOMOUS_TRANSACTION;\t\n" +
                    "\tOBJINVANTES INTEGER;\n" +
                    "\tOBJINVDEPOIS INTEGER;\n" +
                    "\tCURSOR C_OBJ_INVALIDOS IS SELECT      'ALTER '\n" +
                    "\t\t\t|| DECODE (OBJECT_TYPE, 'PACKAGE BODY', 'PACKAGE', OBJECT_TYPE)\n" +
                    "\t\t\t|| ' '\n" +
                    "\t\t\t|| OBJECT_NAME\n" +
                    "\t\t\t|| DECODE (OBJECT_TYPE,\n" +
                    "\t\t\t\t\t\t'PACKAGE BODY', ' COMPILE BODY',\n" +
                    "\t\t\t\t\t\t' COMPILE'\n" +
                    "\t\t\t\t\t) AS COMPILACAO\n" +
                    "\t\tFROM USER_OBJECTS\n" +
                    "\tWHERE STATUS = 'INVALID';\t\n" +
                    "BEGIN\n" +
                    "\tFOR COMANADO IN C_OBJ_INVALIDOS LOOP\n" +
                    "\t\tBEGIN\n" +
                    "\t\t\tEXECUTE IMMEDIATE (COMANADO.COMPILACAO);\n" +
                    "\t\t\tEXCEPTION WHEN OTHERS THEN NULL;\n" +
                    "\t\tEND;\t\t\t\n" +
                    "\tEND LOOP;\n" +
                    "END;\n" +
                    "/\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT OBJETOS INVÁLIDOS (ANTES) \n" +
                    "PROMPT ========================================================================================================\n" +
                    "SELECT OBJECT_TYPE, OBJECT_NAME\n" +
                    "FROM USER_OBJECTS\n" +
                    "WHERE STATUS <> 'VALID' ;\n" +
                    "PROMPT ========================================================================================================\n" +
                    "\n" +
                    scriptMaxpar.getText()+
                    "\n" +
                    "COMMIT;\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT CONCEDENDO PERMISSOES AOS OBJETOS CRIADOS, AGUARDE...\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SET SERVEROUT ON\n" +
                    "DECLARE \n" +
                    "\tPRAGMA AUTONOMOUS_TRANSACTION;\t\n" +
                    "\tOBJINVANTES INTEGER;\n" +
                    "\tOBJINVDEPOIS INTEGER;\n" +
                    "\tCURSOR C_OBJ_GRANTS IS SELECT  'GRANT  '||\n" +
                    "                    DECODE(OBJECT_TYPE,\n" +
                    "            \t\t\t   'TABLE'   ,'SELECT,INSERT,UPDATE,DELETE',\n" +
                    "            \t\t\t   'VIEW'    ,'SELECT',\n" +
                    "            \t\t\t   'SEQUENCE','SELECT',\n" +
                    "            \t\t\t   'EXECUTE'\n" +
                    "            \t\t\t  )||\n" +
                    "            ' ON '||OBJECT_NAME||' TO MXMSYS, MXMDBA' AS PERMISSAO\n" +
                    "            FROM USER_OBJECTS\n" +
                    "            WHERE OBJECT_TYPE IN ('PROCEDURE','FUNCTION','VIEW','TABLE','SEQUENCE','PACKAGE','PACKAGE BODY')\n" +
                    "            AND CREATED > TO_DATE(SYSDATE,'DD/MM/RRRR') - 3\n" +
                    "            ORDER BY 1;\t\n" +
                    "BEGIN\n" +
                    "\tFOR COMANADO IN C_OBJ_GRANTS LOOP\n" +
                    "\t\tBEGIN\t\t    \n" +
                    "\t\t\tEXECUTE IMMEDIATE (COMANADO.PERMISSAO);\n" +
                    "\t\t\tDBMS_OUTPUT.PUT_LINE('--> O COMANDO \"'||COMANADO.PERMISSAO||'\" FOI BEM-SUCEDIDO.');\n" +
                    "\t\t\tEXCEPTION WHEN OTHERS THEN NULL;\n" +
                    "\t\tEND;\t\t\t\n" +
                    "\tEND LOOP;\n" +
                    "END;\n" +
                    "/\n" +
                    "\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT VALIDANDO OBJETOS, AGUARDE...\n" +
                    "PROMPT ========================================================================================================\n" +
                    "DECLARE \n" +
                    "\tPRAGMA AUTONOMOUS_TRANSACTION;\t\n" +
                    "\tOBJINVANTES INTEGER;\n" +
                    "\tOBJINVDEPOIS INTEGER;\n" +
                    "\tCURSOR C_OBJ_INVALIDOS IS SELECT      'ALTER '\n" +
                    "\t\t\t|| DECODE (OBJECT_TYPE, 'PACKAGE BODY', 'PACKAGE', OBJECT_TYPE)\n" +
                    "\t\t\t|| ' '\n" +
                    "\t\t\t|| OBJECT_NAME\n" +
                    "\t\t\t|| DECODE (OBJECT_TYPE,\n" +
                    "\t\t\t\t\t\t'PACKAGE BODY', ' COMPILE BODY',\n" +
                    "\t\t\t\t\t\t' COMPILE'\n" +
                    "\t\t\t\t\t) AS COMPILACAO\n" +
                    "\t\tFROM USER_OBJECTS\n" +
                    "\tWHERE STATUS = 'INVALID';\t\n" +
                    "BEGIN\n" +
                    "\tFOR COMANADO IN C_OBJ_INVALIDOS LOOP\n" +
                    "\t\tBEGIN\n" +
                    "\t\t\tEXECUTE IMMEDIATE (COMANADO.COMPILACAO);\n" +
                    "\t\t\tEXCEPTION WHEN OTHERS THEN NULL;\n" +
                    "\t\tEND;\t\t\t\n" +
                    "\tEND LOOP;\n" +
                    "END;\n" +
                    "/\n" +
                    "\n" +
                    "DECLARE \n" +
                    "\tPRAGMA AUTONOMOUS_TRANSACTION;\t\n" +
                    "\tOBJINVANTES INTEGER;\n" +
                    "\tOBJINVDEPOIS INTEGER;\n" +
                    "\tCURSOR C_OBJ_INVALIDOS IS SELECT      'ALTER '\n" +
                    "         || DECODE (OBJECT_TYPE, 'PACKAGE BODY', 'PACKAGE', OBJECT_TYPE)\n" +
                    "         || ' '\n" +
                    "         || OBJECT_NAME\n" +
                    "         || DECODE (OBJECT_TYPE,\n" +
                    "                    'PACKAGE BODY', ' COMPILE BODY;',\n" +
                    "                    ' COMPILE;'\n" +
                    "                   ) AS COMPILACAO\n" +
                    "    FROM USER_OBJECTS\n" +
                    "   WHERE STATUS = 'INVALID'\n" +
                    "ORDER BY OBJECT_NAME;\t\n" +
                    "BEGIN\n" +
                    "\tFOR COMANADO IN C_OBJ_INVALIDOS LOOP\n" +
                    "\t\tBEGIN\n" +
                    "\t\t\tEXECUTE IMMEDIATE (COMANADO.COMPILACAO);\n" +
                    "\t\t\tEXCEPTION WHEN OTHERS THEN NULL;\n" +
                    "\t\tEND;\t\t\t\n" +
                    "\tEND LOOP;\n" +
                    "END;\n" +
                    "/\n" +
                    "\n" +
                    "EXEC MXMVALIDA_80;\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT OBJETOS INVÁLIDOS (DEPOIS) \n" +
                    "PROMPT ========================================================================================================\n" +
                    "SELECT OBJECT_TYPE, OBJECT_NAME\n" +
                    "FROM USER_OBJECTS\n" +
                    "WHERE STATUS <> 'VALID' ;\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT = DESCRICAO DOS ERROS ENCONTRADOS                                                              \n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT \n" +
                    "SET TRIMSPOOL ON PAGES 0 LINES 10000 LONG 1000000\n" +
                    "SELECT \n" +
                    "'TIPO...: '  ||  UE.TYPE || CHR(10) ||\n" +
                    "'NOME...: '  ||  UE.NAME || CHR(10) ||\n" +
                    "'ERRO...: '  ||  UE.TEXT || CHR(10) ||\n" +
                    "'LINHA..: '  || CHR(10)  || SUBSTR(US.TEXT, UE.POSITION) || CHR(10)\n" +
                    "FROM \n" +
                    "USER_ERRORS UE, USER_SOURCE US \n" +
                    "WHERE UE.TYPE = US.TYPE \n" +
                    "AND   UE.NAME = US.NAME\n" +
                    "AND   UE.LINE = US.LINE\n" +
                    "AND   UE.TYPE <> 'VIEW'\n" +
                    "ORDER BY UE.TYPE, UE.NAME;\n" +
                    "\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT VERIFICANDO VERSAO DO MANAGER\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SELECT * FROM VERSAO_VER ORDER BY 1;\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT VERIFICANDO VERSAO DO ORACLE\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SELECT * FROM V$VERSION;\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT VERIFICANDO DATA DE CRIACAO\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SELECT USER, CREATED FROM USER_USERS;\n" +
                    "\n" +
                    "PROMPT ========================================================================================================\n" +
                    "PROMPT =                                  < FIM DO SCRIPT >                                                   =\n" +
                    "PROMPT ========================================================================================================\n" +
                    "SPOOL OFF\n" +
                    "CL SCR\n" +
                    "PROMPT FAVOR REALIZAR O TESTE NOVAMENTE\n" +
                    "PROMPT CASO O ERRO PERSISTA, FAVOR RETORNAR O LOG QUE FOI GERADO NO CAMINHO \"C:\\temp\\LOG_"+patMaxpar.getText()+".LOG\".\n" +
                    "PROMPT OBRIGADO.\t\t";
        }
        buffWrite.write(estruturaMaxpar);
        buffWrite.flush();
        buffWrite.close();
    }
}