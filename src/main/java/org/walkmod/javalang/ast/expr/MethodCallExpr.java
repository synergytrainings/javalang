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

import org.walkmod.javalang.ast.MethodSymbolData;
import org.walkmod.javalang.ast.Node;
import org.walkmod.javalang.ast.SymbolDefinition;
import org.walkmod.javalang.ast.SymbolReference;
import org.walkmod.javalang.ast.type.Type;
import org.walkmod.javalang.visitors.GenericVisitor;
import org.walkmod.javalang.visitors.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class MethodCallExpr extends Expression implements SymbolReference {

   private Expression scope;

   private List<Type> typeArgs;

   private String name;

   private List<Expression> args;

   private SymbolDefinition symbolDefinition;

   public MethodCallExpr() {
   }

   public MethodCallExpr(Expression scope, String name) {
      setScope(scope);
      this.name = name;
   }

   public MethodCallExpr(Expression scope, String name, List<Expression> args) {
      setScope(scope);
      this.name = name;
      setArgs(args);
   }

   public MethodCallExpr(int beginLine, int beginColumn, int endLine, int endColumn, Expression scope,
         List<Type> typeArgs, String name, List<Expression> args) {
      super(beginLine, beginColumn, endLine, endColumn);
      setScope(scope);
      setTypeArgs(typeArgs);
      this.name = name;
      setArgs(args);
   }

   @Override
   public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
      return v.visit(this, arg);
   }

   @Override
   public <A> void accept(VoidVisitor<A> v, A arg) {
      v.visit(this, arg);
   }

   public List<Expression> getArgs() {
      return args;
   }

   public String getName() {
      return name;
   }

   public Expression getScope() {
      return scope;
   }

   public List<Type> getTypeArgs() {
      return typeArgs;
   }

   public void setArgs(List<Expression> args) {
      this.args = args;
      setAsParentNodeOf(args);
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setScope(Expression scope) {
      if(this.scope != null){
         updateReferences(this.scope);
      }
      this.scope = scope;
      setAsParentNodeOf(scope);
   }

   public void setTypeArgs(List<Type> typeArgs) {
      this.typeArgs = typeArgs;
      setAsParentNodeOf(typeArgs);
   }

   @Override
   public MethodSymbolData getSymbolData() {
      return (MethodSymbolData) super.getSymbolData();
   }

   @Override
   public SymbolDefinition getSymbolDefinition() {
      return symbolDefinition;
   }

   @Override
   public void setSymbolDefinition(SymbolDefinition symbolDefinition) {
      this.symbolDefinition = symbolDefinition;
   }

   @Override
   public boolean replaceChildNode(Node oldChild, Node newChild) {
      boolean updated = false;

      if (oldChild == scope) {
         setScope((Expression) newChild);
         updated = true;
      }
      if (!updated) {
         updated = replaceChildNodeInList(oldChild, newChild, typeArgs);

         if (!updated) {
            updated = replaceChildNodeInList(oldChild, newChild, args);
         }
      }

      return updated;
   }
}
