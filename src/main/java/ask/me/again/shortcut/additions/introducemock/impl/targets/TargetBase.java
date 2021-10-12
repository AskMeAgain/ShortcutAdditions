package ask.me.again.shortcut.additions.introducemock.impl.targets;

import ask.me.again.shortcut.additions.introducemock.impl.IntroduceTarget;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.util.TypeConversionUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class TargetBase implements IntroduceTarget {

  protected final PsiElementFactory factory;
  protected final Project project;
  protected final PsiFile psiFile;

  public TargetBase(PsiFile psiFile, Project project){
    this.project = project;
    this.psiFile = psiFile;
    this.factory = JavaPsiFacade.getElementFactory(project);
  }

  public List<PsiElement> createMockExpressions(PsiParameter[] parameterList, List<Boolean> changeMap){
    var resultList = new ArrayList<PsiElement>();

    for (int i = 0; i < parameterList.length; i++) {
      var psiParameter = parameterList[i];
      if(TypeConversionUtil.isPrimitiveAndNotNull(psiParameter.getType())){
        resultList.add(null);
        changeMap.set(i,false);
      } else if (changeMap.get(i)) {
        resultList.add(createExpression(psiParameter));
      } else {
        resultList.add(null);
      }
    }

    return resultList;
  }

}
