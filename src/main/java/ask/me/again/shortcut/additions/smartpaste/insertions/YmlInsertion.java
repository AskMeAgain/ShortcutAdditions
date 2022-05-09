package ask.me.again.shortcut.additions.smartpaste.insertions;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.intellij.lang.Language;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.CaretState;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.impl.PsiElementFactoryImpl;
import com.intellij.psi.impl.source.tree.OwnBufferLeafPsiElement;
import com.intellij.psi.impl.source.tree.PsiPlainTextImpl;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtil;
import com.intellij.util.IncorrectOperationException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.yaml.YAMLUtil;
import org.jetbrains.yaml.formatter.YamlInjectedBlockFactory;
import org.jetbrains.yaml.parser.YAMLParser;
import org.jetbrains.yaml.psi.YAMLFile;
import org.jetbrains.yaml.psi.YAMLKeyValue;
import org.jetbrains.yaml.psi.YAMLValue;
import org.jetbrains.yaml.psi.YamlRecursivePsiElementVisitor;
import org.jetbrains.yaml.psi.impl.*;
import org.yaml.snakeyaml.Yaml;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class YmlInsertion implements SmartInsertion {

  @Override
  public boolean isApplicable(String originalText, String clipboardText, AnActionEvent e) {
    var psiFile = e.getData(CommonDataKeys.PSI_FILE);
    if (psiFile == null) {
      return false;
    }

    var fileName = psiFile.getName();

    return fileName.endsWith(".yml") || fileName.endsWith(".yaml");
  }

  @Override
  public String apply(String originalText, String clipboard, AnActionEvent e) {
    var psiFile = e.getRequiredData(CommonDataKeys.PSI_FILE);

    var splitted = clipboard.split("=");
    var stack = new LinkedList<>(List.of(splitted[0].split("\\.")));

    var i = 0;
    YAMLKeyValue result = null;
    for (i = 1; i < stack.size(); i++) {
      var temp = YAMLUtil.getQualifiedKeyInFile((YAMLFile) psiFile, stack.subList(0, i));
      if (temp == null) {
        break;
      }
      result = temp;
    }

    var testblock = createYamlBlock("testblock", e);
    var root = PsiTreeUtil.findChildOfType(testblock, YAMLBlockMappingImpl.class);
    result.getParent().getParent().add(root);

    return "";
  }

  private PsiFile createYamlBlock(String blockName, AnActionEvent e) {
    return PsiFileFactory.getInstance(e.getProject()).createFileFromText(Language.findLanguageByID("yaml"), blockName + ":\n");
  }


}