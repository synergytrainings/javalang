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
package org.walkmod.javalang.ast.expr;

import java.util.List;

import org.walkmod.javalang.ast.Node;
import org.walkmod.javalang.ast.type.Type;
import org.walkmod.javalang.visitors.GenericVisitor;
import org.walkmod.javalang.visitors.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class ArrayCreationExpr extends Expression {

   private Type type;

   private int arrayCount;

   private ArrayInitializerExpr initializer;

   private List<Expression> dimensions;

   private List<List<AnnotationExpr>> arraysAnnotations;

   public ArrayCreationExpr() {
   }

   public ArrayCreationExpr(Type type, int arrayCount, ArrayInitializerExpr initializer) {
      setType(type);
      this.arrayCount = arrayCount;
      setInitializer(initializer);
      this.dimensions = null;
   }

   public ArrayCreationExpr(Type type, int arrayCount, ArrayInitializerExpr initializer,
         List<List<AnnotationExpr>> arraysAnnotations) {
      setType(type);
      this.arrayCount = arrayCount;
      setInitializer(initializer);
      this.dimensions = null;
      setArraysAnnotations(arraysAnnotations);
   }

   public ArrayCreationExpr(int beginLine, int beginColumn, int endLine, int endColumn, Type type, int arrayCount,
         ArrayInitializerExpr initializer) {
      super(beginLine, beginColumn, endLine, endColumn);
      setType(type);
      this.arrayCount = arrayCount;
      setInitializer(initializer);
      this.dimensions = null;
   }

   public ArrayCreationExpr(Type type, List<Expression> dimensions, int arrayCount) {
      setType(type);
      this.arrayCount = arrayCount;
      setDimensions(dimensions);
      this.initializer = null;
   }

   public ArrayCreationExpr(Type type, List<Expression> dimensions, int arrayCount,
         List<List<AnnotationExpr>> arraysAnnotations) {
      setType(type);
      this.arrayCount = arrayCount;
      setDimensions(dimensions);
      this.initializer = null;
      setArraysAnnotations(arraysAnnotations);
   }

   public ArrayCreationExpr(int beginLine, int beginColumn, int endLine, int endColumn, Type type,
         List<Expression> dimensions, int arrayCount) {
      super(beginLine, beginColumn, endLine, endColumn);
      setType(type);
      this.arrayCount = arrayCount;
      setDimensions(dimensions);
      this.initializer = null;
   }

   public ArrayCreationExpr(int beginLine, int beginColumn, int endLine, int endColumn, Type type,
         List<Expression> dimensions, int arrayCount, List<List<AnnotationExpr>> arraysAnnotations) {
      super(beginLine, beginColumn, endLine, endColumn);
      setType(type);
      this.arrayCount = arrayCount;
      setDimensions(dimensions);
      this.initializer = null;
      setArraysAnnotations(arraysAnnotations);
   }

   @Override
   public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
      return v.visit(this, arg);
   }

   @Override
   public <A> void accept(VoidVisitor<A> v, A arg) {
      v.visit(this, arg);
   }

   public int getArrayCount() {
      return arrayCount;
   }

   public List<Expression> getDimensions() {
      return dimensions;
   }

   public ArrayInitializerExpr getInitializer() {
      return initializer;
   }

   public Type getType() {
      return type;
   }

   public void setArrayCount(int arrayCount) {
      this.arrayCount = arrayCount;
   }

   public void setDimensions(List<Expression> dimensions) {
      this.dimensions = dimensions;
      setAsParentNodeOf(dimensions);
   }

   public void setInitializer(ArrayInitializerExpr initializer) {
      if(this.initializer != null){
         updateReferences(this.initializer);
      }
      this.initializer = initializer;
      setAsParentNodeOf(initializer);
   }

   public void setType(Type type) {
      if(this.type != null){
         updateReferences(this.type);
      }
      this.type = type;
   }

   public List<List<AnnotationExpr>> getArraysAnnotations() {
      return arraysAnnotations;
   }

   public void setArraysAnnotations(List<List<AnnotationExpr>> arraysAnnotations) {
      this.arraysAnnotations = arraysAnnotations;
      if (arraysAnnotations != null) {
         for (List<AnnotationExpr> ann : arraysAnnotations) {
            setAsParentNodeOf(ann);
         }
      }
   }

   @Override
   public boolean replaceChildNode(Node oldChild, Node newChild) {
      boolean updated = false;
      
      if(type == oldChild){
         setType((Type) newChild);
         updated = true;
      }
      if(!updated){
         if(initializer == oldChild){
            setInitializer((ArrayInitializerExpr) newChild);
            updated = true;
         }
         if(!updated){
            updated = this.replaceChildNodeInList(oldChild, newChild, dimensions);
            
            if(!updated){
               updated = replaceChildNodeInList(oldChild, newChild, arraysAnnotations);
            }
         }
      }
      return updated;
   }
}
