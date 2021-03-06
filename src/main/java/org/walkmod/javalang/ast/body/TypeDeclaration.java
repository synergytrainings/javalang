/* 
  Copyright (C) 2013 Raquel Pau and Albert Coroleu.
 
 Walkmod is free software: you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.
 
 Walkmod is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 
 You should have received a copy of the GNU Lesser General Public License
 along with Walkmod.  If not, see <http://www.gnu.org/licenses/>.*/

package org.walkmod.javalang.ast.body;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.walkmod.javalang.ast.Node;
import org.walkmod.javalang.ast.SymbolData;
import org.walkmod.javalang.ast.SymbolDataAware;
import org.walkmod.javalang.ast.SymbolDefinition;
import org.walkmod.javalang.ast.SymbolReference;
import org.walkmod.javalang.ast.expr.AnnotationExpr;
import org.walkmod.javalang.comparators.TypeDeclarationComparator;
import org.walkmod.merger.MergeEngine;
import org.walkmod.merger.Mergeable;

/**
 * @author Julio Vilmar Gesser
 */
public abstract class TypeDeclaration extends BodyDeclaration
      implements Mergeable<TypeDeclaration>, SymbolDataAware<SymbolData>, SymbolDefinition {

   private String name;

   private int modifiers;

   private List<BodyDeclaration> members;

   private SymbolData symbolData;

   private List<SymbolReference> usages;

   private List<SymbolReference> bodyReferences;

   private int scopeLevel = 0;

   public TypeDeclaration() {
   }

   public TypeDeclaration(int modifiers, String name) {
      this.name = name;
      this.modifiers = modifiers;
   }

   public TypeDeclaration(List<AnnotationExpr> annotations, JavadocComment javaDoc, int modifiers, String name,
         List<BodyDeclaration> members) {
      this.name = name;
      this.modifiers = modifiers;
      setMembers(members);
      setJavaDoc(javaDoc);
      setAnnotations(annotations);
   }

   public TypeDeclaration(int beginLine, int beginColumn, int endLine, int endColumn, List<AnnotationExpr> annotations,
         JavadocComment javaDoc, int modifiers, String name, List<BodyDeclaration> members) {
      super(beginLine, beginColumn, endLine, endColumn, annotations, javaDoc);
      this.name = name;
      this.modifiers = modifiers;
      setMembers(members);
   }

   public final List<BodyDeclaration> getMembers() {
      return members;
   }

   /**
    * Return the modifiers of this type declaration.
    * 
    * @see ModifierSet
    * @return modifiers
    */
   public final int getModifiers() {
      return modifiers;
   }

   public final String getName() {
      return name;
   }

   public void setMembers(List<BodyDeclaration> members) {
      this.members = members;
      setAsParentNodeOf(members);
   }

   public final void setModifiers(int modifiers) {
      this.modifiers = modifiers;
   }

   public final void setName(String name) {
      this.name = name;
   }

   @Override
   public void merge(TypeDeclaration remoteTypeDeclaration, MergeEngine configuration) {
      super.merge(remoteTypeDeclaration, configuration);
      List<BodyDeclaration> resultList = new LinkedList<BodyDeclaration>();
      configuration.apply(getMembers(), remoteTypeDeclaration.getMembers(), resultList, BodyDeclaration.class);
      if (!resultList.isEmpty()) {
         setMembers(resultList);
      } else {
         setMembers(null);
      }
   }

   @Override
   public Comparator<?> getIdentityComparator() {
      return new TypeDeclarationComparator();
   }

   @Override
   public SymbolData getSymbolData() {
      return symbolData;
   }

   @Override
   public void setSymbolData(SymbolData symbolData) {
      this.symbolData = symbolData;
   }

   public List<SymbolReference> getUsages() {
      return usages;
   }

   public List<SymbolReference> getBodyReferences() {
      return bodyReferences;
   }

   public void setUsages(List<SymbolReference> usages) {
      this.usages = usages;
   }

   public void setBodyReferences(List<SymbolReference> bodyReferences) {
      this.bodyReferences = bodyReferences;
   }

   @Override
   public boolean addBodyReference(SymbolReference bodyReference) {
      if (bodyReference != null) {
         SymbolDefinition definition = bodyReference.getSymbolDefinition();
         if (definition != null) {
            int scope = definition.getScopeLevel();
            if (scope <= scopeLevel) {
               if (bodyReferences == null) {
                  bodyReferences = new LinkedList<SymbolReference>();
               }
               return bodyReferences.add(bodyReference);
            }
         }
      }
      return false;
   }

   @Override
   public boolean addUsage(SymbolReference usage) {
      if (usage != null) {
         usage.setSymbolDefinition(this);
         if (usages == null) {
            usages = new LinkedList<SymbolReference>();
         }
         return usages.add(usage);
      }
      return false;

   }

   @Override
   public int getScopeLevel() {
      return scopeLevel;
   }

   @Override
   public void setScopeLevel(int scopeLevel) {
      this.scopeLevel = scopeLevel;
   }

   @Override
   public boolean replaceChildNode(Node oldChild, Node newChild) {

      return replaceChildNodeInList(oldChild, newChild, members);
   }
}
